package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.data.ItemRoomDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.data.Items
import blood.pressure.fingerprint.scanner.bpmonitor.data.Note
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentSettingsBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.CSVWriter
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.isInternetConnection
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import java.util.*


@Suppress("DEPRECATION")
class
FragmentSettings : Fragment() {
    private lateinit var database: ItemRoomDatabase
    private lateinit var reference: SharedPreferencesUtils
    private val exportFile: String = "bpExport"
    private val mTag: String = "bac"
    private val RC_SIGN_IN: Int = 100
    private lateinit var list: MutableList<Items>
    private var listNote: MutableList<String> = mutableListOf()
    private var isSyncing = false

    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_settings, container, false
        )
        reference = SharedPreferencesUtils(requireActivity())
        //set up sign in
        //get data
        database = ItemRoomDatabase.getDatabase(requireContext())
        database.itemDAO().getAll().observe(requireActivity(), Observer {
            list = it as MutableList<Items>
        })
        database.noteDAO().getAll().observe(requireActivity(), Observer {
            for (note in it) {
                listNote.add(note.name)
            }
        })

        //change language
        binding.language.setOnClickListener {
            startActivity(Intent(requireActivity(), LanguageActivity::class.java))
        }

        return binding.root
    }

    private fun backupAndRestore() {

        Common.pushEventAnalytics(" backup")

        if (!isInternetConnection()) {
            AlertDialog.Builder(requireActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setCancelable(true)
                .setMessage(resources.getString(R.string.no_internet))
                .setNegativeButton(resources.getString(R.string.cancel)) { _, _ ->
                }
                .setPositiveButton(
                    R.string.retry
                ) { _, _ -> backupAndRestore() }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        } else {

        }
    }

    //this function download file from FireStore and call store function
    private fun restoreData() {

        val ONE_MEGABYTE: Long = 1024 * 1024

    }

    //this function remove all local record and insert data from FireStore

    private fun getNewItemEntry(
        itemSystolic: Int,
        itemDiastolic: Int,
        itemPulse: Int,
        itemLevel: String,
        itemNote: String,
        itemDate: String
    ): Items {
        return Items(
            itemSYS = itemSystolic,
            itemDIA = itemDiastolic,
            itemPulse = itemPulse,
            itemLevel = itemLevel,
            itemNote = itemNote,
            itemDate = itemDate
        )
    }








    private fun openConfirmSignOutDialog() {
        val dialog = Dialog(requireActivity())
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
        val cancel = window.findViewById<TextView>(R.id.tv_cancel_confirm)
        val signOut = window.findViewById<TextView>(R.id.tv_confirm)
        val title = window.findViewById<TextView>(R.id.tv_title_confirm_dialog)

        title.text = resources.getString(R.string.confirm_sign_out)
        signOut.text = resources.getString(R.string.sign_out)
        //initComponents

        //init event
        signOut.setOnClickListener {
            dialog.dismiss()
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)

        //show dialog
        dialog.show()
    }

    private fun upFile() {


    }

    @SuppressLint("InflateParams")
    private fun customToast(content: String) {
        val toast = Toast(App.appContext)
        toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
        val inflater =
            App.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.sync_toast, null)
        val title = view.findViewById<TextView>(R.id.tv_toast_content)
        title.text = content
        toast.view = view
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }

    private fun animRotate(view: View) {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.repeatCount = Animation.INFINITE
        rotate.duration = 400
        rotate.interpolator = LinearInterpolator()
        view.startAnimation(rotate)
    }

    private fun updateSyncTime() {
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val calendar =
            Calendar.getInstance(MyUtil.getCurrentLocale(App.appContext))
        reference.putStringValue(
            SharedPreferencesUtils.KEY_LAST_SYNC,
            String.format(
                "%02d",
                calendar.get(Calendar.MONTH) + 1
            ) + "-" + String.format(
                "%02d",
                calendar.get(Calendar.DAY_OF_MONTH)
            ) + " " + String.format(
                "%02d",
                calendar.get(Calendar.HOUR_OF_DAY)
            ) + ":" + String.format("%02d", calendar.get(Calendar.MINUTE))
        )
    }
}
