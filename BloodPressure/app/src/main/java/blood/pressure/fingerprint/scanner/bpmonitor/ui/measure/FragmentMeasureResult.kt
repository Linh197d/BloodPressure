package blood.pressure.fingerprint.scanner.bpmonitor.ui.measure

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ITypeAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.TypeAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.Level
import blood.pressure.fingerprint.scanner.bpmonitor.data.Type
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentMeasureResultBinding
import blood.pressure.fingerprint.scanner.bpmonitor.ui.InfoDetailActivity
import blood.pressure.fingerprint.scanner.bpmonitor.viewmodel.AddViewModel
import blood.pressure.fingerprint.scanner.bpmonitor.viewmodel.AddViewModelFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FragmentMeasureResult : Fragment(), ITypeAdapter {

    private lateinit var binding: FragmentMeasureResultBinding
    private lateinit var parent: FragmentWatcherContainer
    private lateinit var date: String

    private val viewModel: AddViewModel by viewModels {
        AddViewModelFactory(
            (activity?.application as App).database.itemDAO()
        )
    }

    @SuppressLint("SimpleDateFormat")
    var df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    private var today: Date = Calendar.getInstance().time

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_measure_result,
            container,
            false
        )

        date = df.format(today)
        binding.datetime.text = date

        parent = parentFragment as FragmentWatcherContainer
        binding.pulse.text = "${parent.result}"
        binding.sys.text = "${parent.sp}"
        binding.dia.text = "${parent.dp}"

        Log.d("bacdz", "${parent.sp}/${parent.dp}")
        checkIndexBlood(parent.sp-20, parent.dp-10)

        animationView()

        binding.reload.setOnClickListener {
            @Suppress("DEPRECATION")
            Handler().postDelayed({
                parent.sp = 90
                parent.dp = 60
                parent.result = 60
                parent.level = Level.Normal
                parent.replaceFragment(FragmentMeasureProgress())
            }, 150)
        }

        binding.linear1.setOnClickListener {
            openTypeDialog()
        }

        @Suppress("DEPRECATION")
        Handler().postDelayed({ openConfirmDialog() }, 1000)

        return binding.root
    }


    private fun informationHypertension() {
        binding.imgHideHypotension.visibility = View.INVISIBLE
        binding.imgHideNormal.visibility = View.INVISIBLE
        binding.imgHideElevated.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage1.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage2.visibility = View.INVISIBLE
        binding.imgHideHypertension.visibility = View.VISIBLE

        binding.imgColor.setImageResource(R.drawable.circle_hypertension)
        binding.tvInformation.setText(R.string.hypertensive)
        binding.tvIntros.setText(R.string.intros_hypertensive)
        binding.tvNote.setText(R.string.note_hypertensive)

        parent.level = Level.Hypertensive
        binding.emoji.setAnimation(R.raw.sad)
    }

    private fun informationHypertensionStage2() {
        binding.imgHideHypotension.visibility = View.INVISIBLE
        binding.imgHideNormal.visibility = View.INVISIBLE
        binding.imgHideElevated.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage1.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage2.visibility = View.VISIBLE
        binding.imgHideHypertension.visibility = View.INVISIBLE

        binding.imgColor.setImageResource(R.drawable.circle_hypertension_stage2)
        binding.tvInformation.setText(R.string.hypertension_stage_2)
        binding.tvIntros.setText(R.string.intros_hypertension_stage2)
        binding.tvNote.setText(R.string.note_hypertension_stage2)

        parent.level = Level.Hypertension_2
        binding.emoji.setAnimation(R.raw.sad)
    }

    private fun informationHypertensionStage1() {
        binding.imgHideHypotension.visibility = View.INVISIBLE
        binding.imgHideNormal.visibility = View.INVISIBLE
        binding.imgHideElevated.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage1.visibility = View.VISIBLE
        binding.imgHideHypertensionStage2.visibility = View.INVISIBLE
        binding.imgHideHypertension.visibility = View.INVISIBLE

        binding.imgColor.setImageResource(R.drawable.circle_hypertension_stage1)
        binding.tvInformation.setText(R.string.hypertension_stage_1)
        binding.tvIntros.setText(R.string.intros_hypertension_stage1)
        binding.tvNote.setText(R.string.note_hypertension_stage1)

        parent.level = Level.Hypertension_1
        binding.emoji.setAnimation(R.raw.sad)
    }

    private fun informationHypotension() {
        binding.imgHideHypotension.visibility = View.VISIBLE
        binding.imgHideNormal.visibility = View.INVISIBLE
        binding.imgHideElevated.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage1.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage2.visibility = View.INVISIBLE
        binding.imgHideHypertension.visibility = View.INVISIBLE

        binding.imgColor.setImageResource(R.drawable.circle_hypotension)
        binding.tvInformation.setText(R.string.hypotension)
        binding.tvIntros.setText(R.string.intros_hypotension)
        binding.tvNote.setText(R.string.note_hypotension)

        parent.level = Level.Hypotension
        binding.emoji.setAnimation(R.raw.sad)

    }

    private fun informationNormal() {
        binding.imgHideHypotension.visibility = View.INVISIBLE
        binding.imgHideNormal.visibility = View.VISIBLE
        binding.imgHideElevated.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage1.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage2.visibility = View.INVISIBLE
        binding.imgHideHypertension.visibility = View.INVISIBLE
        binding.imgColor.setImageResource(R.drawable.circle_normal)
        binding.tvInformation.setText(R.string.normal)
        binding.tvIntros.setText(R.string.intros_normal)
        binding.tvNote.setText(R.string.note_normal)

        parent.level = Level.Normal
        binding.emoji.setAnimation(R.raw.happy)
    }

    private fun informationElevated() {
        binding.imgHideHypotension.visibility = View.INVISIBLE
        binding.imgHideNormal.visibility = View.INVISIBLE
        binding.imgHideElevated.visibility = View.VISIBLE
        binding.imgHideHypertensionStage1.visibility = View.INVISIBLE
        binding.imgHideHypertensionStage2.visibility = View.INVISIBLE
        binding.imgHideHypertension.visibility = View.INVISIBLE

        binding.imgColor.setImageResource(R.drawable.circle_elevated)
        binding.tvInformation.setText(R.string.elevated)
        binding.tvIntros.setText(R.string.intros_elevated)
        binding.tvNote.setText(R.string.note_elevated)

        parent.level = Level.Elevated
        binding.emoji.setAnimation(R.raw.happy)
    }

    private fun checkIndexBlood(index1: Int, index2: Int) {
        if (index1 < 90 || index2 < 60) {
            informationHypotension()
        } else {
            when (index2) {
                in 60..79 -> {
                    when (index1) {
                        in 90..119 -> informationNormal()
                        in 120..129 -> informationElevated()
                        in 130..139 -> informationHypertensionStage1()
                        in 140..180 -> informationHypertensionStage2()
                        in 181..300 -> informationHypertension()
                    }
                }
                in 80..89 -> informationHypertensionStage1()
                in 90..120 -> informationHypertensionStage2()
                in 121..200 -> informationHypertension()
            }
        }
    }

    private fun openTypeDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.type_dialog)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //setup position of dialog
        val windowAttribute = window.attributes
        windowAttribute.gravity = Gravity.CENTER
        window.attributes = windowAttribute

        //initView
        val recyclerView = window.findViewById<RecyclerView>(R.id.rv_type)
        val cancel = window.findViewById<AppCompatButton>(R.id.cancel_type_dialog)

        //initComponents
        val listType = ArrayList<Type>()
        listType.add(
            Type(
                R.color.color_hypotension,
                App.appContext.getString(R.string.hypotension),
                App.appContext.getString(R.string.intros_hypotension)
            )
        )
        listType.add(
            Type(
                R.color.color_normal,
                App.appContext.getString(R.string.normal),
                App.appContext.getString(R.string.intros_normal)
            )
        )
        listType.add(
            Type(
                R.color.color_elevated,
                App.appContext.getString(R.string.elevated),
                App.appContext.getString(R.string.intros_elevated)
            )
        )
        listType.add(
            Type(
                R.color.color_hypertension_stage1,
                App.appContext.getString(R.string.hypertension_stage_1),
                App.appContext.getString(R.string.intros_hypertension_stage1)
            )
        )
        listType.add(
            Type(
                R.color.color_hypertension_stage2,
                App.appContext.getString(R.string.hypertension_stage_2),
                App.appContext.getString(R.string.intros_hypertension_stage2)
            )
        )
        listType.add(
            Type(
                R.color.color_hypertension,
                App.appContext.getString(R.string.hypertensive),
                App.appContext.getString(R.string.intros_hypertensive)
            )
        )
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        val adapter = TypeAdapter(requireActivity(), listType, this)
        recyclerView.adapter = adapter

        //init event
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(true)

        //show dialog
        dialog.show()
    }

    override fun onTypeClick(position: Int) {
        val intent = Intent(requireActivity(), InfoDetailActivity::class.java)
        intent.putExtra("color", R.color.color_know)
        intent.putExtra("type", position + 2)
        intent.putExtra("title", resources.getString(R.string.know_bp_numbers))
        intent.putExtra("image", R.drawable.ic_know)
        startActivity(intent)
    }

    private fun animationView() {

        binding.apply {
            emoji.translationY = 70f
            reload.translationY = 70f
            emoji.alpha = 0f
            reload.alpha = 0f
            emoji.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(750)
                .start()
            reload.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(750)
                .start()
        }
    }

    private fun openConfirmDialog() {
        Log.d("bacdz","show save dialog")
        val dialog = Dialog(parent.requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.confirm_dialog)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //setup position of dialog
        val windowAttribute = window.attributes
        windowAttribute.gravity = Gravity.CENTER
        window.attributes = windowAttribute

        //initView
        val text = window.findViewById<TextView>(R.id.tv_title_confirm_dialog)
        val no = window.findViewById<TextView>(R.id.tv_cancel_confirm)
        val yes = window.findViewById<TextView>(R.id.tv_confirm)

        text.text = App.appContext.getString(R.string.save_confirm)
        yes.text = App.appContext.getString(R.string.save)

        //init event
        no.setOnClickListener { dialog.dismiss() }

        yes.setOnClickListener {
            addNewItem()
            dialog.dismiss()
            Toast.makeText(
                requireActivity(),
                App.appContext.getString(R.string.saved),
                Toast.LENGTH_SHORT
            ).show()
        }

        dialog.setCancelable(true)
        //show dialog
        dialog.show()
    }

    private fun addNewItem() {
        viewModel.addNewItem(
            parent.sp,
            parent.dp,
            parent.result,
            parent.level,
            "",
            date
        )
    }
}