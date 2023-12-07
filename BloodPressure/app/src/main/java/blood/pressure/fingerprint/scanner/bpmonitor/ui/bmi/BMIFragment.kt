package blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.WeightPickerAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentBMIBinding
import com.cncoderx.wheelview.OnWheelChangedListener
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager

class BMIFragment : Fragment() {

    private lateinit var binding: FragmentBMIBinding
    private lateinit var weightAdapter: WeightPickerAdapter
    private lateinit var gender: String
    private var height = 160
    private var weight = 50

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_b_m_i,
            container,
            false
        )

        val parent = parentFragment as FragmentBMIContainer

        gender = getString(R.string.male)
        animationView()
//        Gender
        val titlesOfGender: List<String> = listOf(
            getString(R.string.female),
            getString(R.string.unknown),
            getString(R.string.male)
        )

        binding.genderWheelView.apply {
            titles = titlesOfGender
            elevation = 0f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                isFocusedByDefault = true
            }
            isSelected = true
            focusedIndex = 2
        }

        binding.genderWheelView.selectListener = {
            gender = titlesOfGender[0]
        }

//        Weight
        val pickerLayoutManager =
            PickerLayoutManager(requireActivity(), PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.apply {
            isChangeAlpha = true
            scaleDownBy = 0.99f
            scaleDownDistance = 0.8f
            initialPrefetchItemCount = 3
            isSmoothScrollbarEnabled = true
            scrollToPosition(49)
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.weightRecyclerBtn)

        weightAdapter =
            WeightPickerAdapter(requireActivity(), getData(151), binding.weightRecyclerBtn)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.weightRecyclerBtn.defaultFocusHighlightEnabled = true
        }
        binding.weightRecyclerBtn.apply {
            layoutManager = pickerLayoutManager
            adapter = weightAdapter
            isSelected = true
            requestFocus()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                isFocusedByDefault = true
            }
        }

        pickerLayoutManager.setOnScrollStopListener { view ->
            weight = Integer.parseInt((view as TextView).text.toString())
        }

//        Height
        binding.heightWheel.currentIndex = 159
        binding.heightWheel.onWheelChangedListener =
            OnWheelChangedListener { view, _, newIndex ->
                val text = view.getItem(newIndex)
                height = Integer.parseInt(text.toString())
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.heightWheel.apply {
                defaultFocusHighlightEnabled = true
            }
        }

        binding.submit.setOnClickListener {
            animationViewUp()
            @Suppress("DEPRECATION")
            Handler().postDelayed({
                parent.height = height
                parent.weight = weight
                if (gender == getString(R.string.male)) {
                    parent.gender = 0
                } else {
                    parent.gender = 1
                }
                parent.replaceFragment(FragmentBMIResult())
            }, 600)
        }

        return binding.root
    }

    private fun getData(count: Int): List<String> {
        val data: MutableList<String> = ArrayList()
        for (i in 0 until count) {
            data.add(i.toString())
        }
        return data
    }

    private fun animationView() {
        binding.apply {
            bodyContainer.translationY = 150f
            footerContainer.translationY = 150f
            heightWheel.translationY = 150f
            weightRecyclerBtn.translationX = 150f

            bodyContainer.alpha = 0f
            footerContainer.alpha = 0f
            heightWheel.alpha = 0f
            weightRecyclerBtn.alpha = 0f

            bodyContainer.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(300)
                .start()
            footerContainer.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(400)
                .start()
            heightWheel.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(450)
                .start()
            weightRecyclerBtn.animate().translationX(0f).alpha(1f).setDuration(500)
                .setStartDelay(500).start()
        }
    }

    private fun animationViewUp() {
        binding.apply {
            textView.animate().translationY(0f).alpha(0f).setDuration(50).setStartDelay(0).start()
            bodyContainer.animate().translationY(-250f).alpha(0f).setDuration(500).setStartDelay(0)
                .start()
            footerContainer.animate().translationY(-250f).alpha(0f).setDuration(500)
                .setStartDelay(50).start()
            heightWheel.animate().translationY(-250f).alpha(0f).setDuration(500).setStartDelay(100)
                .start()
            weightRecyclerBtn.animate().translationX(-250f).alpha(0f).setDuration(500)
                .setStartDelay(150).start()
        }
    }
}