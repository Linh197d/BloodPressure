package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentInfoBinding

@Suppress("DEPRECATION")
class FragmentInfo : Fragment() {

    companion object {
        const val INFO_KNOW = "know"
        const val INFO_LEARN = "learn"
        const val INFO_FIND = "find"
        const val INFO_BREAK = "break"
        const val INFO_TYPE = "type"
        const val INFO_NOTICE = "notice"
        const val INFO_PROBLEM = "problem"
        const val INFO_UNDERSTAND = "understand"
        const val INFO_DRUG_HYPER = "drugsHyper"
        const val INFO_CONTROL = "control"
        const val INFO_LOWER = "lower"
        const val INFO_DIAGNOSE = "dia"
        const val INFO_DRUG_HYPO = "drugHypo"
        const val INFO_TIPS_HYPER = "tipsHyper"
        const val INFO_TIPS_HYPO = "tipHypo"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentInfoBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_info, container, false
        )

        val rootView = binding.root

        binding.info1.setOnClickListener {
            putData(
                R.color.color_know,
                binding.infoTitle1.text.toString(),
                INFO_KNOW,
                R.drawable.ic_know
            )
        }
        binding.info2.setOnClickListener {
            putData(
                R.color.color_elevated,
                binding.infoTitle2.text.toString(),
                INFO_LEARN,
                R.drawable.ic_learnabout
            )
        }
        binding.info3.setOnClickListener {
            putData(
                R.color.color_hypertension,
                binding.infoTitle3.text.toString(),
                INFO_TYPE,
                R.drawable.ic_findyour
            )
        }
        binding.info4.setOnClickListener {
            putData(
                R.color.color_normal,
                binding.infoTitle4.text.toString(),
                INFO_BREAK,
                R.drawable.ic_doctor
            )
        }
        binding.info5.setOnClickListener {
            putData(
                R.color.color_hypotension,
                binding.infoTitle5.text.toString(),
                INFO_TYPE,
                R.drawable.ic_hypertension
            )
        }
        binding.info6.setOnClickListener {
            putData(
                R.color.color_elevated,
                binding.infoTitle6.text.toString(),
                INFO_NOTICE,
                R.drawable.ic_notice
            )
        }
        binding.info7.setOnClickListener {
            putData(
                R.color.color_know,
                binding.infoTitle7.text.toString(),
                INFO_PROBLEM,
                R.drawable.ic_know_problems
            )
        }
        binding.info8.setOnClickListener {
            putData(
                R.color.color_hypertension,
                binding.infoTitle8.text.toString(),
                INFO_UNDERSTAND,
                R.drawable.ic_understand
            )
        }
        binding.info9.setOnClickListener {
            putData(
                R.color.color_normal,
                binding.infoTitle9.text.toString(),
                INFO_DRUG_HYPER,
                R.drawable.ic_first_line1
            )
        }
        binding.info10.setOnClickListener {
            putData(
                R.color.color_hypotension,
                binding.infoTitle10.text.toString(),
                INFO_CONTROL,
                R.drawable.ic_control
            )
        }
        binding.info11.setOnClickListener {
            putData(
                R.color.color_elevated,
                binding.infoTitle11.text.toString(),
                INFO_LOWER,
                R.drawable.ic_lower
            )
        }
        binding.info12.setOnClickListener {
            putData(
                R.color.color_know,
                binding.infoTitle12.text.toString(),
                INFO_DIAGNOSE,
                R.drawable.ic_howto
            )
        }
        binding.info13.setOnClickListener {
            putData(
                R.color.color_hypertension,
                binding.infoTitle13.text.toString(),
                INFO_DRUG_HYPO,
                R.drawable.ic_first_line2
            )
        }
        binding.info14.setOnClickListener {
            putData(
                R.color.color_normal,
                binding.infoTitle14.text.toString(),
                INFO_TIPS_HYPER,
                R.drawable.ic_first_aid1
            )
        }
        binding.info15.setOnClickListener {
            putData(
                R.color.color_hypotension,
                binding.infoTitle15.text.toString(),
                INFO_TIPS_HYPO,
                R.drawable.ic_first_aid2
            )
        }
        return binding.root
    }

    private fun putData(color: Int, title: String, content: String, image: Int) {
        val intent = Intent(activity, InfoDetailActivity::class.java)
        intent.putExtra("color", color)
        intent.putExtra("content", content)
        intent.putExtra("title", title)
        intent.putExtra("image", image)
        startActivityForResult(intent, 3)
    }
}