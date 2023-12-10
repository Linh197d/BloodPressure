package blood.pressure.fingerprint.scanner.bpmonitor.ui.measure

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Camera
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentMeasureProgressBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.ImageProcessing
import blood.pressure.fingerprint.scanner.bpmonitor.util.Math.Fft
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.ceil
import kotlin.math.pow

class FragmentMeasureProgress : Fragment() {

    private lateinit var binding: FragmentMeasureProgressBinding

    private val processing = AtomicBoolean(false)
    private lateinit var previewHolder: SurfaceHolder

    @Suppress("DEPRECATION")
    private var camera: Camera? = null
    private lateinit var wakeLock: PowerManager.WakeLock
    private var beats = 0
    private var bufferAvgB = 0.0
    private var progressP = 0
    var inc = 0
    private var st: Long = 0
    private var startTime: Long = 0
    private var samplingFreq = 0.0
    private var greenAvgList = ArrayList<Double>()
    private var redAvgList = ArrayList<Double>()
    private var counter = 0

    //BloodPressure variables
    private var gender = 0.0  //BloodPressure variables
    private var age = 0.0  //BloodPressure variables
    private var height = 0.0  //BloodPressure variables
    private var weight = 0.0
    private var q = 4.5
    private var sp: Int = 0
    private var dp: Int = 0

    private var isNext = false

    private lateinit var parent: FragmentWatcherContainer

    @SuppressLint("InvalidWakeLockTag")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_measure_progress,
            container,
            false
        )

        parent = parentFragment as FragmentWatcherContainer

        height = App.kv.decodeInt(SharedPreferencesUtils.HEIGHT, 0).toDouble()
        weight = App.kv.decodeInt(SharedPreferencesUtils.WEIGHT, 0).toDouble()
        age = App.kv.decodeInt(SharedPreferencesUtils.AGE, 0).toDouble()
        gender = App.kv.decodeInt(SharedPreferencesUtils.GENDER, 0).toDouble()

        q = if (gender == 0.0) {
            5.0
        } else {
            4.5
        }

        binding.heartSign.setAnimation(R.raw.heart_beat_sign)

        previewHolder = binding.preview.holder
        previewHolder.addCallback(surfaceCallback)
        @Suppress("DEPRECATION")
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        binding.progress.progress = 0
        // WakeLock Initialization : Forces the phone to stay On
        val pm = requireActivity().getSystemService(Context.POWER_SERVICE) as PowerManager
        //noinspection deprecation
        @Suppress("DEPRECATION")
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen")

        binding.cancel.setOnClickListener {
            parent.result = 60
            parent.replaceFragment(FragmentStartMeasure())
        }
        return binding.root
    }

    //Prevent the system from restarting your activity during certain configuration changes,
    // but receive a callback when the configurations do change, so that you can manually update your activity as necessary.
    //such as screen orientation, keyboard availability, and language

    //Wakelock + Open device camera + set orientation to 90 degree
    //store system time as a start time for the analyzing process
    //your activity to start interacting with the user.
    // This is a good place to begin animations, open exclusive-access devices (such as the camera)
    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()
        wakeLock.acquire(10 * 60 * 1000L /*5 minutes*/)
        camera = Camera.open()
        camera?.setDisplayOrientation(90)
        startTime = System.currentTimeMillis()
        st = System.currentTimeMillis()
    }

    //call back the frames then release the camera + wakelock and Initialize the camera to null
    //Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been killed. The counterpart to onResume().
    //When activity B is launched in front of activity A,
    // this callback will be invoked on A. B will not be created until A's onPause() returns, so be sure to not do anything lengthy here.
    @Suppress("DEPRECATION")
    override fun onPause() {
        super.onPause()
        wakeLock.release()
        camera?.setPreviewCallback(null)
        camera?.stopPreview()
        camera?.release()
        camera = null
    }

    //getting frames data from the camera and start the heartbeat process
    @Suppress("DEPRECATION")
    private val previewCallback =
        Camera.PreviewCallback { data, cam ->
            /**
             * {@inheritDoc}
             */
            //if data or size == null ****
            if (data == null) throw NullPointerException()
            @Suppress("DEPRECATION") val size =
                cam.parameters.previewSize ?: throw NullPointerException()

            //Atomically sets the value to the given updated value if the current value == the expected value.
            if (!processing.compareAndSet(
                    false,
                    true
                )
            ) return@PreviewCallback

            //put width + height of the camera inside the variables
            val width = size.width
            val height = size.height
            val greenAvg: Double = ImageProcessing.decodeYUV420SPtoRedBlueGreenAvg(
                data.clone(),
                height,
                width,
                3
            ) //1 stands for red intensity, 2 for blue, 3 for green
            val redAvg: Double = ImageProcessing.decodeYUV420SPtoRedBlueGreenAvg(
                data.clone(),
                height,
                width,
                1
            ) //1 stands for red intensity, 2 for blue, 3 for green
            greenAvgList.add(greenAvg)
            redAvgList.add(redAvg)
            ++counter //counts number of frames in 30 seconds


            //To check if we got a good red intensity to process if not return to the condition and set it again until we get a good red intensity
            if (redAvg < 200) {
                st = System.currentTimeMillis()
                inc = 0
                progressP = inc
                counter = 0
                binding.progress.progress = progressP
                processing.set(false)

            }
            val endTime = System.currentTimeMillis()
            val totalTimeInSecs: Double =
                (endTime - startTime) / 1000.0 //to convert time to seconds
            if (totalTimeInSecs >= 20) { //when 30 seconds of measuring passes do the following " we chose 30 seconds to take half sample since 60 seconds is normally a full sample of the heart beat
                val green = greenAvgList.toTypedArray()
                val red = redAvgList.toTypedArray()
                samplingFreq = counter / totalTimeInSecs //calculating the sampling frequency
                val hRFreq: Double = Fft.FFT(
                    green,
                    counter,
                    samplingFreq
                ) // send the green array and get its fft then return the amount of heart rate per second
                val bpm = ceil(hRFreq * 60).toInt().toDouble()
                val hR1Freq: Double = Fft.FFT(
                    red,
                    counter,
                    samplingFreq
                ) // send the red array and get its fft then return the amount of heart rate per second
                val bpm1 = ceil(hR1Freq * 60).toInt().toDouble()

                // The following code is to make sure that if the heart rate from red and green intensities are reasonable
                // take the average between them, otherwise take the green or red if one of them is good
                if (bpm > 45 || bpm < 200) {
                    bufferAvgB = if (bpm1 > 45 || bpm1 < 200) {
                        (bpm + bpm1) / 2
                    } else {
                        bpm
                    }
                } else if (bpm1 > 45 || bpm1 < 200) {
                    bufferAvgB = bpm1
                }
                if (bufferAvgB < 45 || bufferAvgB > 200) { //if the heart beat wasn't reasonable after all reset the progress bar and restart measuring
                    st = System.currentTimeMillis()
                    inc = 0
                    progressP = inc
                    binding.progress.progress = progressP
                    Toast.makeText(
                        App.appContext,
                        getString(R.string.measure_fail),
                        Toast.LENGTH_SHORT
                    ).show()
                    startTime =
                        System.currentTimeMillis()
                    counter = 0
                    processing.set(false)
                    return@PreviewCallback
                }
                //calculate
                beats = bufferAvgB.toInt()
                val rob = 18.5
                val et: Double = 364.5 - 1.23 * beats
                val bsa = 0.007184 * weight.pow(0.425) * this.height.pow(0.725)
                val sv: Double = -6.6 + 0.25 * (et - 35) - 0.62 * beats + 40.4 * bsa - 0.51 * age
                val pp: Double = sv / (0.013 * weight - 0.007 * age - 0.004 * beats + 1.307)
                val mpp = q * rob

                sp = (mpp + 3 / 2 * pp).toInt()
                dp = (mpp - pp / 3).toInt()

                if (beats != 0 && sp != 0 && dp != 0) { //if beasts were reasonable stop the loop and send HR with the username to results activity and finish this activity
                    parent.result = beats
                    parent.sp = sp
                    parent.dp = dp
                    if (!isNext) {
                        parent.replaceFragment(FragmentMeasureResult())
                        isNext = true
                    }
                }

            }

            if (redAvg != 0.0) { //increment the progress bar
                inc = ((System.currentTimeMillis() - st) / 10).toInt()
                //progressP = inc++
                progressP = inc
                Log.d("vinhm", "$inc")
                binding.progress.progress = progressP
            }

            //keeps taking frames tell 30 seconds
            processing.set(false)

        }

    @Suppress("DEPRECATION")
    private val surfaceCallback: SurfaceHolder.Callback = object : SurfaceHolder.Callback {
        override fun surfaceCreated(holder: SurfaceHolder) {
            try {
                camera?.setPreviewDisplay(previewHolder)
                camera?.setPreviewCallback(
                    previewCallback
                )
            } catch (t: Throwable) {
                Log.e("PreviewDemo-surfaceCallback", "Exception in setPreviewDisplay()", t)
            }
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            val parameters: Camera.Parameters =
                camera!!.parameters
            parameters.flashMode = Camera.Parameters.FLASH_MODE_TORCH
            val size = getSmallestPreviewSize(width, height, parameters)
            if (size != null) {
                parameters.setPreviewSize(size.width, size.height)
                Log.d(
                    tag,
                    "Using width=" + size.width + " height=" + size.height
                )
            }
            camera?.parameters = parameters
            camera?.startPreview()
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {}
    }

    @Suppress("DEPRECATION")
    private fun getSmallestPreviewSize(
        width: Int,
        height: Int,
        parameters: Camera.Parameters
    ): Camera.Size? {
        var result: Camera.Size? = null
        for (size in parameters.supportedPreviewSizes) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size
                } else {
                    val resultArea = result.width * result.height
                    val newArea = size.width * size.height
                    if (newArea < resultArea) result = size
                }
            }
        }
        return result
    }
}