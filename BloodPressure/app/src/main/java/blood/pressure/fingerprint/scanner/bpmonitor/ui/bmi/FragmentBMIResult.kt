package blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentBMIResultBinding

class FragmentBMIResult : Fragment() {

    private lateinit var binding: FragmentBMIResultBinding
    private var weight: Double = 1.0
    private var height: Double = 1.0
    private var result: Double = 0.0
    private var gender: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_b_m_i_result,
            container,
            false
        )

        val parent = parentFragment as FragmentBMIContainer
        weight = parent.weight.toDouble()
        height = parent.height.toDouble()
        gender = parent.gender

        bmiCal()
        animationView()
        binding.reloadBtn.setOnClickListener {
            animationViewUp()
            @Suppress("DEPRECATION")
            Handler().postDelayed({
                parent.height = 160
                parent.weight = 50
                parent.gender = 1
                parent.replaceFragment(BMIFragment())
            }, 600)
        }

        return binding.root
    }

    private fun animationView() {

        binding.apply {

            deskImage.translationY = 100f
            resultText.translationY = 40f
            bmiText.translationY = 50f
            bmiTextNormal.translationY = 50f
            reloadBtn.translationY = 70f
            emoji.translationY = 70f

            deskImage.alpha = 0f
            resultText.alpha = 0f
            bmiText.alpha = 0f
            bmiTextNormal.alpha = 0f
            reloadBtn.alpha = 0f
            emoji.alpha = 0f

            deskImage.setPadding(100)

            deskImage.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(300)
                .start()
            deskImage.setPadding(0)
            resultText.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(500)
                .start()
            bmiText.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(450).start()
            bmiTextNormal.animate().translationY(0f).alpha(.3f).setDuration(500).setStartDelay(500)
                .start()
            reloadBtn.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(750)
                .start()
            emoji.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(750)
                .start()
        }
    }

    private fun animationViewUp() {
        binding.apply {

            textView.animate().translationY(0f).alpha(0f).setDuration(500).setStartDelay(0)
                .start()
            deskImage.animate().translationY(-250f).alpha(0f).setDuration(500).setStartDelay(0)
                .start()

            resultText.animate().translationY(-250f).alpha(0f).setDuration(500).setStartDelay(50)
                .start()
            bmiText.animate().translationY(-250f).alpha(0f).setDuration(500).setStartDelay(100)
                .start()
            bmiTextNormal.animate().translationY(-250f).alpha(0f).setDuration(500)
                .setStartDelay(150)
                .start()
            reloadBtn.animate().translationY(-250f).alpha(0f).setDuration(300)
                .setStartDelay(250).start()
            emoji.animate().translationY(-250f).alpha(0f).setDuration(300)
                .setStartDelay(250).start()
        }
    }


    private fun bmiCal() {
        if (height > 0 && weight > 0) {
            if (gender == 0) {
                bmiCalMale()
            } else if (gender == 1) {
                bmiCalFemale()
            }
            showResult()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun showResult() {

        val solution = String.format("%.1f", result)
        binding.resultText.text = solution
        binding.bmiText.apply {
            if (result < 18.5) {
                this.text = getString(R.string.under_weight)
                binding.emoji.setAnimation(R.raw.sad)
            } else if (result >= 18.5 && result < 24.9) {
                this.text = getString(R.string.health)
                binding.emoji.setAnimation(R.raw.happy)
            } else if (result >= 24.9 && result < 30) {
                this.text = getString(R.string.over_weight)
                binding.emoji.setAnimation(R.raw.sad)
            } else if (result >= 30) {
                this.text = getString(R.string.obesity)
                binding.emoji.setAnimation(R.raw.sad)
            }
        }
    }

    private fun bmiCalMale() {
        result = ((weight / (height * height)) * 10000)
    }

    private fun bmiCalFemale() {
        result = ((weight / (height * height)) * 10000)
    }
}