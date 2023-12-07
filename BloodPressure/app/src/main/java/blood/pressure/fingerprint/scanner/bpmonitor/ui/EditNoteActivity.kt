package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.EditNoteAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.RecyclerItemMoveCallBack
import blood.pressure.fingerprint.scanner.bpmonitor.data.ItemRoomDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.data.Note
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityEditNoteBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@SuppressLint("NewApi")
class EditNoteActivity : LocaleAwareCompatActivity(), IAdapter {
    private var _binding: ActivityEditNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: ItemRoomDatabase
    private val list = ArrayList<String>()
    private val oldList = ArrayList<String>()

    private lateinit var adapter: EditNoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyUtil.setStatusBar(this)

        initViews()
        binding.cancelEditNoteDialog.setOnClickListener {
            finish()
        }

        binding.btnAddNewNote.setOnClickListener {
            openAddNoteDialog()
        }

        binding.btnSaveEditNote.setOnClickListener {
            if (list != oldList) {
                saveNoteChange()
            }
            val returnIntent = Intent()
            setResult(RESULT_OK, returnIntent)
            finish()
        }

    }

    private fun initViews() {
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.alignItems = AlignItems.CENTER
        layoutManager.justifyContent = JustifyContent.CENTER
        binding.rvEditNotes.layoutManager = layoutManager
        database = ItemRoomDatabase.getDatabase(this)
        database.noteDAO().getAll().observe(this, Observer {
            list.clear()
            for (note in it) {
                list.add(note.name)
            }
            oldList.addAll(list)
            adapter = EditNoteAdapter(list, this)
            //drag item
            val callback = RecyclerItemMoveCallBack(adapter)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(binding.rvEditNotes)

            binding.rvEditNotes.adapter = adapter
        })

    }

    override fun onItemClick(position: Int) {

    }

    override fun onItemClick2(value: String) {
        openConfirmDeleteDialog(value)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openConfirmDeleteDialog(note: String) {
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
            list.remove(note)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)

        //show dialog
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openAddNoteDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.input_dialog)
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
        val cancel = window.findViewById<TextView>(R.id.tv_cancel_input_dialog)
        val save = window.findViewById<TextView>(R.id.tv_save_input)
        val input = window.findViewById<EditText>(R.id.et_input_text_dialog)
        input.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        //initComponents

        //init event
        save.setOnClickListener {
            if (input.text.toString() != "") {
                list.add(input.text.toString())
                adapter.notifyDataSetChanged()
                //hide keyboard
                //imm.hideSoftInputFromWindow(this.currentFocus?.applicationWindowToken, 0)
                dialog.dismiss()
            }
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(true)
        dialog.setOnDismissListener {
            imm.hideSoftInputFromWindow(this.currentFocus?.applicationWindowToken, 0)
        }
        //show dialog
        dialog.show()
    }

    private fun saveNoteChange() {
        CoroutineScope(Dispatchers.IO).launch {
            database.noteDAO().deleteAll()
            val iterator = list.iterator()
            while (iterator.hasNext()) {
                val note = iterator.next()
                database.noteDAO().insert(Note(note))
            }
        }
    }
}