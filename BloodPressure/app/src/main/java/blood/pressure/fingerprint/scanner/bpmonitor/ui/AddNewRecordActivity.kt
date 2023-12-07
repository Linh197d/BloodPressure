package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.NoteAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ITypeAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.TypeAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.data.ItemRoomDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.data.Items
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.Level
import blood.pressure.fingerprint.scanner.bpmonitor.data.Type
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityAddNewRecordBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.getCurrentLocale
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.setStatusBar
import blood.pressure.fingerprint.scanner.bpmonitor.viewmodel.AddViewModel
import blood.pressure.fingerprint.scanner.bpmonitor.viewmodel.AddViewModelFactory
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.lang.reflect.Field
import java.time.*
//import java.time.YearMonth
import java.util.*
import kotlin.collections.ArrayList
import org.threeten.bp.*
import org.threeten.bp.YearMonth
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
class AddNewRecordActivity : LocaleAwareCompatActivity(), IAdapter, ITypeAdapter {
    private var _binding: ActivityAddNewRecordBinding? = null
    private val binding get() = _binding!!
    private var level: String = Level.Normal
    private lateinit var database: ItemRoomDatabase
    private val viewModel: AddViewModel by viewModels {
        AddViewModelFactory(
            (this.application as App).database.itemDAO()
        )
    }

    private lateinit var adapter: NoteAdapter
    private val l = arrayListOf("")
    private var selected: MutableList<String> = mutableListOf()

    private var firstLaunch: Boolean = true
    private val delaySpinner: Long = 300L
    private var listMonth = arrayListOf<String>()
    private var listHour = arrayListOf<String>()
    private var listMinute = arrayListOf<String>()
    private var index: Int = 0
    lateinit var item: Items

    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var hour: Int = 0
    private var minute: Int = 0

    private var indexSystolic: Int = HYPOTHESIS_SYS
    private var indexDiastolic: Int = HYPOTHESIS_DIA
    private var indexPulse: Int = INDEX_PULSE_MIN

    private var indexYear: Int = 0
    private var indexMonth: Int = 0

    private var indexHour: Int = 0
    private var indexMinute: Int = 0

    companion object {
        const val INDEX_SYS_MIN: Int = 20
        const val INDEX_SYS_MAX: Int = 250

        const val INDEX_DIA_MIN: Int = 20
        const val INDEX_DIA_MAX: Int = 200

        const val INDEX_PULSE_MIN: Int = 20
        const val INDEX_PULSE_MAX: Int = 200

        const val HYPOTHESIS_SYS: Int = 90
        const val HYPOTHESIS_DIA: Int = 60
        const val PULSE: Int = 70
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddNewRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBar(this)
        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = ContextCompat.getDrawable(this, R.drawable.rounded_text_view)
        layers[1] = ContextCompat.getDrawable(this, R.drawable.bg_after_onclick)

        initViews()

        binding.numberSystolic.setOnValueChangedListener { _, _, newVal ->
            indexSystolic = newVal
//            if (isIndex) {
//            }
            checkIndexBlood(indexSystolic, indexDiastolic)
        }
        binding.numberDiastolic.setOnValueChangedListener { _, _, newVal ->
            indexDiastolic = newVal
            checkIndexBlood(indexSystolic, indexDiastolic)
        }

        binding.year.setOnValueChangedListener { picker, _, newVal ->
            val handler = Handler()
            handler.postDelayed({
                if (newVal == picker.value) {
                    indexYear = newVal
                    listMonth.clear()
                    listHour.clear()
                    listMinute.clear()
                    checkIndexDateTime(indexYear, indexHour)
                }
            }, delaySpinner)
        }
        binding.monthDay.setOnValueChangedListener { picker, _, newVal ->
            val handler = Handler()
            handler.postDelayed({
                if (newVal == picker.value) {
                    indexMonth = newVal
                    listMonth.clear()
                    listHour.clear()
                    listMinute.clear()
                    checkIndexDateTime(indexYear, indexHour)
                }
            }, delaySpinner)
        }

        binding.hour.setOnValueChangedListener { picker, _, newVal ->
            val handler = Handler()
            handler.postDelayed({
                if (newVal == picker.value) {
                    indexHour = newVal
                    listMonth.clear()
                    listHour.clear()
                    listMinute.clear()
                    checkIndexDateTime(indexYear, indexHour)
                }
            }, delaySpinner)
        }

        binding.minues.setOnValueChangedListener { picker, _, newVal ->
            val handler = Handler()
            handler.postDelayed({
                if (newVal == picker.value) {
                    indexMinute = newVal
                }
            }, delaySpinner)
        }

        binding.imgExit1.setOnClickListener { view ->
            MyUtil.selectedAnimate(view, layers)
            val returnIntent = Intent()
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        binding.deleteRecord.setOnClickListener { view ->
            MyUtil.selectedAnimate(view, layers)
            if (intent.getStringExtra("action").equals("edit")) {
                openConfirmDeleteDialog()
            }
        }

        binding.numberPulse.setOnValueChangedListener { _, _, newVal ->
            indexPulse = newVal
        }

        binding.btnSave.setOnClickListener {
            when (indexDiastolic < indexSystolic) {
                true -> {
                    if (intent.getStringExtra("action").equals("add")) {
                        //add
                        saveInfo()
                    } else {
                        //update
                        updateInfo()
                    }
                }
                false -> Toast.makeText(
                    this,
                    getString(R.string.input_warning_1),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.linear1.setOnClickListener {
            openTypeDialog()
        }

        binding.note.setOnClickListener {
            openNoteDialog()
        }

    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.numberSystolic.value,
            binding.numberDiastolic.value,
            binding.numberPulse.value,
            binding.tvInformation.text.toString(),
            convertNote(adapter.getSelectedList()),
            "${indexYear}-" + String.format(
                "%02d-%s ", convertMonthToIndex(
                    binding.monthDay.displayedValues[binding.monthDay.value].split(
                        " "
                    )[0]
                ), binding.monthDay.displayedValues[binding.monthDay.value].split(
                    " "
                )[1]
            )
                    + String.format(
                "%02d:%02d",
                indexHour,
                indexMinute
            )
        )
    }

    private fun addNewItem() {
        if (isEntryValid()) {
            index++

            viewModel.addNewItem(
                binding.numberSystolic.value,
                binding.numberDiastolic.value,
                binding.numberPulse.value,
                level,
                convertNote(adapter.getSelectedList()),
                "${indexYear}-" + String.format(
                    "%02d-%s ", convertMonthToIndex(
                        binding.monthDay.displayedValues[binding.monthDay.value].split(
                            " "
                        )[0]
                    ), binding.monthDay.displayedValues[binding.monthDay.value].split(
                        " "
                    )[1]
                )

                        + String.format(
                    "%02d:%02d",
                    indexHour,
                    indexMinute
                )
            )
        }
    }

    private fun updateItem() {
        if (isEntryValid()) {
            viewModel.updateNewItem(
                intent.getIntExtra("id", 0),
                binding.numberSystolic.value,
                binding.numberDiastolic.value,
                binding.numberPulse.value,
                level,
                convertNote(adapter.getSelectedList()),
                "${indexYear}-" + String.format(
                    "%02d-%s ", convertMonthToIndex(
                        binding.monthDay.displayedValues[binding.monthDay.value].split(
                            " "
                        )[0]
                    ), binding.monthDay.displayedValues[binding.monthDay.value].split(
                        " "
                    )[1]
                )

                        + String.format(
                    "%02d:%02d",
                    indexHour,
                    indexMinute
                )
            )
        }
    }

    private fun saveInfo() {

        Common.pushEventAnalytics("save")
    }

    private fun updateInfo() {

        Common.pushEventAnalytics("update")

        updateItem()
        val returnIntent = Intent()
        setResult(RESULT_OK, returnIntent)
        finish()
    }

    private fun initViews() {
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val c =
            Calendar.getInstance(getCurrentLocale(this))
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH) + 1
        day = c.get(Calendar.DAY_OF_MONTH)
        hour = c.get(Calendar.HOUR_OF_DAY)
        minute = c.get(Calendar.MINUTE)

        indexYear = c.get(Calendar.YEAR)
        indexMonth = c.get(Calendar.MONTH)
        indexHour = c.get(Calendar.HOUR_OF_DAY)
        indexMinute = c.get(Calendar.MINUTE)

        database = ItemRoomDatabase.getDatabase(this)
        database.noteDAO().getAll().observe(this, Observer {
            for (note in it) {
                l.add(note.name)
            }
        })
        adapter = NoteAdapter(
            this,
            l, this
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.numberSystolic.textColor = Color.WHITE
            binding.numberDiastolic.textColor = Color.WHITE
            binding.numberPulse.textColor = Color.WHITE
            binding.year.textColor = Color.WHITE
            binding.monthDay.textColor = Color.WHITE
            binding.hour.textColor = Color.WHITE
            binding.minues.textColor = Color.WHITE
        } else {
            setNumberPickerTextColor(binding.numberSystolic, Color.WHITE)
            setNumberPickerTextColor(binding.numberDiastolic, Color.WHITE)
            setNumberPickerTextColor(binding.numberPulse, Color.WHITE)
            setNumberPickerTextColor(binding.year, Color.WHITE)
            setNumberPickerTextColor(binding.monthDay, Color.WHITE)
            setNumberPickerTextColor(binding.hour, Color.WHITE)
            setNumberPickerTextColor(binding.minues, Color.WHITE)
        }

        binding.numberSystolic.maxValue = INDEX_SYS_MAX
        binding.numberSystolic.minValue = INDEX_SYS_MIN

        binding.numberDiastolic.maxValue = INDEX_DIA_MAX
        binding.numberDiastolic.minValue = INDEX_DIA_MIN

        binding.numberPulse.maxValue = INDEX_PULSE_MAX
        binding.numberPulse.minValue = INDEX_PULSE_MIN

        binding.year.maxValue = year
        binding.year.minValue = year - 5

        if (intent.getStringExtra("action").equals("add")) {
            binding.deleteRecord.isInvisible = true
            binding.numberSystolic.value = HYPOTHESIS_SYS
            binding.numberDiastolic.value = HYPOTHESIS_DIA
            binding.numberPulse.value = PULSE
            binding.year.value = year
            checkIndexDateTime(year, hour)
            binding.hour.value = hour
        } else {
            Log.i("bac", "${intent.getIntExtra("id", 0)}")
            viewModel.getItemById(intent.getIntExtra("id", 0))
            viewModel.item.observe(this, Observer {
                if (it != null) {
                    binding.tvInformation.text = it.itemLevel
                    binding.numberSystolic.value = it.itemSYS
                    binding.numberDiastolic.value = it.itemDIA
                    binding.numberPulse.value = it.itemPulse
                    checkIndexBlood(binding.numberSystolic.value, binding.numberDiastolic.value)

                    binding.year.value = it.itemDate.split("-")[0].toInt()
                    checkIndexDateTime(year, hour)

                    //set note value
                    if (it.itemNote != "") {
                        for (s in it.itemNote.trim().split("#")) {
                            if (s != "")
                                selected.add(s.trim())
                        }
                        adapter.setSelectedList(selected)
                        Log.i("bac", selected.toString())
                        binding.note.text = String.format(
                            "%d %s",
                            adapter.getSelectedList().size,
                            resources.getString(R.string.note)
                        )
                    }
                    //bug multi language, fix later
                    Log.i("bac", it.itemDate)
                    Log.i(
                        "bac", convertMonth2(
                            it.itemDate.split(" ")[0].split("-")[1]
                        ) + " " + it.itemDate.split(" ")[0].split(
                            "-"
                        )[2]
                    )
                    binding.monthDay.value = listMonth.indexOf(
                        convertMonth2(
                            it.itemDate.split(" ")[0].split("-")[1]
                        ) + " " + it.itemDate.split(" ")[0].split(
                            "-"
                        )[2]
                    )
                    checkIndexDateTime(year, hour)

                    binding.hour.value = listHour.indexOf(it.itemDate.split(" ")[1].split(":")[0])
                    binding.minues.value =
                        listMinute.indexOf(it.itemDate.split(" ")[1].split(":")[1])
                    Log.i("bac", it.itemDate.split(" ")[1].split(":")[0])
                }
            })
        }
        binding.numberSystolic.wrapSelectorWheel = false
        binding.numberDiastolic.wrapSelectorWheel = false
        binding.numberPulse.wrapSelectorWheel = false
        binding.year.wrapSelectorWheel = false
        binding.monthDay.wrapSelectorWheel = false
        binding.hour.wrapSelectorWheel = false
        binding.minues.wrapSelectorWheel = false
    }

    private fun checkIndexBlood(index1: Int, index2: Int) {
        if (index1 < HYPOTHESIS_SYS || index2 < HYPOTHESIS_DIA) {
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

    private fun checkIndexDateTime(
        indexYear: Int,
        indexHour: Int
    ) {
        //when indexYear change
        if (indexYear == year) {
            for (m in 1..month) {
                val yearMonthObject: YearMonth = YearMonth.of(indexYear, m)
                val daysInMonth: Int = yearMonthObject.lengthOfMonth()
                if (m == month) {
                    for (d in 1..day) {
                        listMonth.add(String.format("%s %02d", convertMonth(m), d))
                    }
                } else {
                    for (d in 1..daysInMonth) {
                        listMonth.add(String.format("%s %02d", convertMonth(m), d))
                    }
                }
            }
            binding.monthDay.displayedValues = null
            binding.monthDay.minValue = 0
            binding.monthDay.maxValue = listMonth.size - 1
            binding.monthDay.displayedValues = listMonth.toTypedArray()
            if (firstLaunch) {
                binding.monthDay.value = listMonth.size - 1
                firstLaunch = false
            }
            //hour
            binding.hour.displayedValues = null
            binding.hour.minValue = 0

            if (binding.monthDay.displayedValues[binding.monthDay.value].equals(
                    String.format(
                        "%s %02d",
                        convertMonth(month),
                        day
                    )
                )
            ) {
                binding.hour.maxValue = hour
                for (h in 0..hour) {
                    listHour.add(String.format("%02d", h))
                }
            } else {
                binding.hour.maxValue = 23
                for (h in 0..23) {
                    listHour.add(String.format("%02d", h))
                }
            }
            binding.hour.displayedValues = listHour.toTypedArray()
            //minute
            binding.minues.displayedValues = null
            binding.minues.minValue = 0
            if (indexHour == hour && binding.monthDay.displayedValues[binding.monthDay.value].equals(
                    String.format(
                        "%s %02d",
                        convertMonth(month),
                        day
                    )
                )
            ) {
                binding.minues.maxValue = minute
                for (m in 0..minute) {
                    listMinute.add(String.format("%02d", m))
                }
                binding.minues.value = minute
            } else {
                binding.minues.maxValue = 59
                for (m in 0..59) {
                    listMinute.add(String.format("%02d", m))
                }
            }
            binding.minues.displayedValues = listMinute.toTypedArray()
        } else {
            for (m in 0..11) {
                val yearMonthObject: YearMonth = YearMonth.of(indexYear, m + 1)
                val daysInMonth: Int = yearMonthObject.lengthOfMonth()
                for (d in 1..daysInMonth) {
                    listMonth.add(String.format("%s %02d", convertMonth(m), d))
                }
                for (h in 0..23) {
                    listHour.add(String.format("%02d", h))
                }
                for (mn in 0..59) {
                    listMinute.add(String.format("%02d", mn))
                }
            }
            binding.monthDay.displayedValues = null
            binding.monthDay.minValue = 0
            binding.monthDay.maxValue = listMonth.size - 1
            binding.monthDay.displayedValues = listMonth.toTypedArray()

            binding.hour.displayedValues = null
            binding.hour.maxValue = 23
            binding.hour.minValue = 0
            binding.hour.displayedValues = listHour.toTypedArray()

            binding.minues.displayedValues = null
            binding.minues.maxValue = 59
            binding.minues.minValue = 0
            binding.minues.displayedValues = listMinute.toTypedArray()
        }
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

        level = Level.Hypertensive
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

        level = Level.Hypertension_2
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

        level = Level.Hypertension_1
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

        level = Level.Hypotension

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

        level = Level.Normal
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

        level = Level.Elevated
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openNoteDialog() {
        selected.clear()
        selected.addAll(adapter.getSelectedList())

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.note_dialog)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.attributes.windowAnimations = R.style.DialogAnimation
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //setup position of dialog
        val windowAttribute = window.attributes
        windowAttribute.gravity = Gravity.BOTTOM
        window.attributes = windowAttribute

        //initView
        val cancel = window.findViewById<ImageView>(R.id.cancel_note_dialog)
        val save = window.findViewById<Button>(R.id.btn_save_note)
        val rv = window.findViewById<RecyclerView>(R.id.rv_notes)

        //initComponents
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.alignItems = AlignItems.CENTER
        layoutManager.justifyContent = JustifyContent.CENTER
        rv.layoutManager = layoutManager
        rv.adapter = adapter
        //init event
        save.setOnClickListener {
            if (adapter.getSelectedList().size == 0) {
                binding.note.text = resources.getString(R.string.note)
            } else {
                binding.note.text = String.format(
                    "%d %s",
                    adapter.getSelectedList().size,
                    resources.getString(R.string.note)
                )
            }
            dialog.dismiss()
            Log.i("bac", selected.toString())
            Log.i("bac", adapter.getSelectedList().toString())
        }
        cancel.setOnClickListener {
            adapter.setSelectedList(selected)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
            Log.i("bac", selected.toString())
            Log.i("bac", adapter.getSelectedList().toString())
        }
        dialog.setCancelable(false)

        //show dialog
        dialog.show()
    }

    private fun openConfirmDeleteDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.confirm_dialog)
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
        val cancel = window.findViewById<TextView>(R.id.tv_cancel_confirm)
        val delete = window.findViewById<TextView>(R.id.tv_confirm)

        //initComponents

        //init event
        delete.setOnClickListener {
            viewModel.removeItemByID(intent.getIntExtra("id", 0))
            dialog.dismiss()
            val returnIntent = Intent()
            setResult(RESULT_OK, returnIntent)
            finish()
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)

        //show dialog
        dialog.show()
    }

    private fun openTypeDialog() {
        val dialog = Dialog(this)
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
                resources.getString(R.string.hypotension),
                resources.getString(R.string.intros_hypotension)
            )
        )
        listType.add(
            Type(
                R.color.color_normal,
                resources.getString(R.string.normal),
                resources.getString(R.string.intros_normal)
            )
        )
        listType.add(
            Type(
                R.color.color_elevated,
                resources.getString(R.string.elevated),
                resources.getString(R.string.intros_elevated)
            )
        )
        listType.add(
            Type(
                R.color.color_hypertension_stage1,
                resources.getString(R.string.hypertension_stage_1),
                resources.getString(R.string.intros_hypertension_stage1)
            )
        )
        listType.add(
            Type(
                R.color.color_hypertension_stage2,
                resources.getString(R.string.hypertension_stage_2),
                resources.getString(R.string.intros_hypertension_stage2)
            )
        )
        listType.add(
            Type(
                R.color.color_hypertension,
                resources.getString(R.string.hypertensive),
                resources.getString(R.string.intros_hypertensive)
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = TypeAdapter(this, listType, this)
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
        val intent = Intent(this, InfoDetailActivity::class.java)
        intent.putExtra("color", R.color.color_know)
        intent.putExtra("type", position + 2)
        intent.putExtra("title", resources.getString(R.string.know_bp_numbers))
        intent.putExtra("image", R.drawable.ic_know)
        startActivity(intent)
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, EditNoteActivity::class.java)
        startActivityForResult(intent, 2)
    }

    override fun onItemClick2(value: String) {
        Log.i("bac", selected.toString() + " " + adapter.getSelectedList().toString())
    }

    private fun convertNote(notes: List<String>): String {
        var tmpString = ""
        for (aValue in notes) {
            tmpString += "#$aValue "
        }
        return tmpString
    }

    @SuppressLint("SoonBlockedPrivateApi")
    fun setNumberPickerTextColor(numberPicker: NumberPicker, color: Int) {
        try {
            val selectorWheelPaintField: Field = numberPicker.javaClass
                .getDeclaredField("mSelectorWheelPaint")
            selectorWheelPaintField.isAccessible = true
            (selectorWheelPaintField.get(numberPicker) as Paint).color = color
        } catch (e: NoSuchFieldException) {
            Log.w("setNumberPickerTextColor", e)
        } catch (e: IllegalAccessException) {
            Log.w("setNumberPickerTextColor", e)
        } catch (e: IllegalArgumentException) {
            Log.w("setNumberPickerTextColor", e)
        }
        val count = numberPicker.childCount
        for (i in 0 until count) {
            val child = numberPicker.getChildAt(i)
            if (child is EditText) child.setTextColor(color)
        }
        numberPicker.invalidate()
    }

    private fun convertMonth(month: Int): String {
        when (month) {
            1 -> return resources.getString(R.string.January)
            2 -> return resources.getString(R.string.February)
            3 -> return resources.getString(R.string.March)
            4 -> return resources.getString(R.string.April)
            5 -> return resources.getString(R.string.May)
            6 -> return resources.getString(R.string.June)
            7 -> return resources.getString(R.string.July)
            8 -> return resources.getString(R.string.August)
            9 -> return resources.getString(R.string.September)
            10 -> return resources.getString(R.string.October)
            11 -> return resources.getString(R.string.November)
            12 -> return resources.getString(R.string.December)
        }
        return ""
    }

    private fun convertMonth2(month: String): String {
        when (month) {
            "01" -> return resources.getString(R.string.January)
            "02" -> return resources.getString(R.string.February)
            "03" -> return resources.getString(R.string.March)
            "04" -> return resources.getString(R.string.April)
            "05" -> return resources.getString(R.string.May)
            "06" -> return resources.getString(R.string.June)
            "07" -> return resources.getString(R.string.July)
            "08" -> return resources.getString(R.string.August)
            "09" -> return resources.getString(R.string.September)
            "10" -> return resources.getString(R.string.October)
            "11" -> return resources.getString(R.string.November)
            "12" -> return resources.getString(R.string.December)
        }
        return ""
    }

    private fun convertMonthToIndex(month: String): Int {
        when (month) {
            resources.getString(R.string.January) -> return 1
            resources.getString(R.string.February) -> return 2
            resources.getString(R.string.March) -> return 3
            resources.getString(R.string.April) -> return 4
            resources.getString(R.string.May) -> return 5
            resources.getString(R.string.June) -> return 6
            resources.getString(R.string.July) -> return 7
            resources.getString(R.string.August) -> return 8
            resources.getString(R.string.September) -> return 9
            resources.getString(R.string.October) -> return 10
            resources.getString(R.string.November) -> return 11
            resources.getString(R.string.December) -> return 12
        }
        return 0
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            database.noteDAO().getAll().observe(this, Observer {
                l.clear()
                l.add("")
                for (note in it) {
                    l.add(note.name)
                    Log.i("bac", note.name + " result")
                }
                //adapter.setList(l)
                adapter.notifyDataSetChanged()
            })

        }
    }
}