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

    // Ngăn hệ thống khởi động lại hoạt động của bạn trong những thay đổi cấu hình nhất định,
    // nhưng nhận được lệnh gọi lại khi cấu hình thay đổi, để bạn có thể cập nhật hoạt động của mình theo cách thủ công nếu cần.
    //chẳng hạn như hướng màn hình, tính khả dụng của bàn phím và ngôn ngữ
    // Wakelock + Mở camera thiết bị + đặt hướng thành 90 độ
    //lưu trữ thời gian hệ thống làm thời gian bắt đầu cho quá trình phân tích
    // hoạt động của bạn để bắt đầu tương tác với người dùng.
    // Đây là nơi tốt để bắt đầu hoạt ảnh, mở các thiết bị có quyền truy cập độc quyền (chẳng hạn như máy ảnh)
    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()
        wakeLock.acquire(10 * 60 * 1000L /*5 minutes*/)
        camera = Camera.open()
        camera?.setDisplayOrientation(90)
        startTime = System.currentTimeMillis()
        st = System.currentTimeMillis()
    }

    // gọi lại các khung sau đó nhả camera + Wakelock và Khởi tạo camera thành null
    // Được gọi như một phần của vòng đời hoạt động khi một hoạt động chuyển sang chế độ nền nhưng chưa (chưa) bị hủy. Bản sao của onResume().
    // Khi hoạt động B được khởi chạy trước hoạt động A,
    // lệnh gọi lại này sẽ được gọi trên A. B sẽ không được tạo cho đến khi onPause() của A trả về, vì vậy hãy đảm bảo không làm gì dài dòng ở đây.
    @Suppress("DEPRECATION")
    override fun onPause() {
        super.onPause()
        wakeLock.release()
        camera?.setPreviewCallback(null)
        camera?.stopPreview()
        camera?.release()
        camera = null
    }

    // lấy dữ liệu khung hình từ camera và bắt đầu quá trình đo nhịp tim
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

// Đặt giá trị nguyên tử thành giá trị cập nhật đã cho nếu giá trị hiện tại == giá trị mong đợi.
if (!processing.compareAndSet(
                    false,
                    true
                )
            ) return@PreviewCallback

            //đặt chiều rộng + chiều cao của máy ảnh vào trong các biến
            val width = size.width
            val height = size.height
            val greenAvg: Double = ImageProcessing.decodeYUV420SPtoRedBlueGreenAvg(
                data.clone(),
                height,
                width,
                3
            ) //1 là cường độ màu đỏ, 2 là màu xanh lam, 3 là màu xanh lá cây
            val redAvg: Double = ImageProcessing.decodeYUV420SPtoRedBlueGreenAvg(
                data.clone(),
                height,
                width,
                1
            ) //1 là cường độ màu đỏ, 2 là màu xanh lam, 3 là màu xanh lá cây
            greenAvgList.add(greenAvg)
            redAvgList.add(redAvg)
            ++counter //đếm số khung hình trong 30 giây


            //Để kiểm tra xem chúng ta có cường độ đỏ tốt để xử lý hay không nếu không quay trở lại điều kiện và đặt lại cho đến khi chúng ta có cường độ đỏ tốt
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
            if (totalTimeInSecs >= 20) { //khi đo 30 giây, hãy làm như sau " chúng tôi chọn 30 giây để lấy một nửa mẫu vì 60 giây thường là mẫu đầy đủ của nhịp tim
                val green = greenAvgList.toTypedArray()
                val red = redAvgList.toTypedArray()
                samplingFreq = counter / totalTimeInSecs //calculating the sampling frequency
                val hRFreq: Double = Fft.FFT(
                    green,
                    counter,
                    samplingFreq
                ) // gửi mảng màu xanh lá cây và nhận fft của nó sau đó trả về lượng nhịp tim mỗi giây
                val bpm = ceil(hRFreq * 60).toInt().toDouble()
                val hR1Freq: Double = Fft.FFT(
                    red,
                    counter,
                    samplingFreq
                ) // gửi mảng màu đỏ và nhận fft của nó sau đó trả về lượng nhịp tim mỗi giây
                val bpm1 = ceil(hR1Freq * 60).toInt().toDouble()

                // Đoạn code sau nhằm đảm bảo rằng nếu nhịp tim từ cường độ đỏ và xanh là hợp lý
                // lấy giá trị trung bình giữa chúng, nếu không thì lấy màu xanh hoặc đỏ nếu một trong số chúng tốt
                if (bpm > 45 || bpm < 200) {
                    bufferAvgB = if (bpm1 > 45 || bpm1 < 200) {
                        (bpm + bpm1) / 2
                    } else {
                        bpm
                    }
                } else if (bpm1 > 45 || bpm1 < 200) {
                    bufferAvgB = bpm1
                }
                if (bufferAvgB < 45 || bufferAvgB > 200) {
            //nếu nhịp tim không hợp lý thì hãy đặt lại thanh tiến trình và bắt đầu lại quá trình đo                    st = System.currentTimeMillis()
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
                //sp là tâm thu, dp là tâm trương
                sp = (mpp + 3 / 2 * pp).toInt()
                dp = (mpp - pp / 3).toInt()

                if (beats != 0 && sp != 0 && dp != 0) {
// nếu điều đó hợp lý, hãy dừng vòng lặp và gửi HR với tên người dùng để kết quả hoạt động và hoàn thành hoạt động này                    parent.result = beats
                    parent.sp = sp
                    parent.dp = dp
                    if (!isNext) {
                        parent.replaceFragment(FragmentMeasureResult())
                        isNext = true
                    }
                }

            }

            if (redAvg != 0.0) { //tăng thanh tiến trình
                inc = ((System.currentTimeMillis() - st) / 10).toInt()
                //progressP = inc++
                progressP = inc
                Log.d("vinhm", "$inc")
                binding.progress.progress = progressP
            }

            //tiếp tục chụp khung hình trong 30 giây
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