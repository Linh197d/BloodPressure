package blood.pressure.fingerprint.scanner.bpmonitor.ui.measure

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import blood.pressure.fingerprint.scanner.bpmonitor.App.Companion.kv
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentStartMeasureBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils

class FragmentStartMeasure : Fragment() {

    private lateinit var binding: FragmentStartMeasureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_start_measure, container, false
        )

        binding.howToMeasure.setOnClickListener {
            @Suppress("DEPRECATION") Handler().postDelayed({
                openInstructionDialog()
            }, 150)
        }

        binding.startMeasure.setOnClickListener {
            //go to measure
            requestCameraPermission()
        }

        binding.info.setOnClickListener {
            openInfoDialog()
        }

        return binding.root
    }

    private fun openInstructionDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.measure_dialog)
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
        val gotIt = window.findViewById<AppCompatButton>(R.id.got_it)

        //init event
        gotIt.setOnClickListener {
            @Suppress("DEPRECATION") Handler().postDelayed({
                dialog.dismiss()
            }, 150)
        }
        dialog.setCancelable(true)

        //show dialog
        dialog.show()
    }

    private fun openInfoDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.body_info_dialog)
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
        val next = window.findViewById<AppCompatButton>(R.id.next)
        val gender = window.findViewById<TextView>(R.id.gender)
        val age = window.findViewById<EditText>(R.id.age)
        val height = window.findViewById<EditText>(R.id.height)
        val weight = window.findViewById<EditText>(R.id.weight)

        //initEvent
        gender.setOnClickListener { view -> showMenuGender(view, gender) }
        val gen = kv.decodeInt(SharedPreferencesUtils.GENDER, -1)
        gender.text = if (gen == -1) {
            ""
        } else {
            when (gen) {
                0 -> getString(R.string.male)
                1 -> getString(R.string.female)
                else -> {
                    getString(R.string.unknown)
                }
            }
        }

        age.setText(
            if (kv.decodeInt(SharedPreferencesUtils.AGE, -1) == -1) {
                ""
            } else {
                "${kv.decodeInt(SharedPreferencesUtils.AGE, -1)}"
            }
        )

        height.setText(
            if (kv.decodeInt(SharedPreferencesUtils.HEIGHT, -1) == -1) {
                ""
            } else {
                "${kv.decodeInt(SharedPreferencesUtils.HEIGHT, -1)}"
            }
        )

        weight.setText(
            if (kv.decodeInt(SharedPreferencesUtils.WEIGHT, -1) == -1) {
                ""
            } else {
                "${kv.decodeInt(SharedPreferencesUtils.WEIGHT, -1)}"
            }
        )

        next.setOnClickListener {
            if (gender.text.toString() != "" && age.text.toString() != "" && height.text.toString() != "" && weight.text.toString() != "") {
                val g = if (gender.text.toString() == getString(R.string.male)) {
                    0
                } else if (gender.text.toString() == getString(R.string.female)) {
                    1
                } else {
                    2
                }
                kv.encode(SharedPreferencesUtils.GENDER, g)
                kv.encode(SharedPreferencesUtils.AGE, Integer.parseInt(age.text.toString()))
                kv.encode(SharedPreferencesUtils.HEIGHT, Integer.parseInt(height.text.toString()))
                kv.encode(SharedPreferencesUtils.WEIGHT, Integer.parseInt(weight.text.toString()))
                dialog.dismiss()
            }
        }
        dialog.setCancelable(false)

        //show dialog
        dialog.show()
    }

    private fun requestCameraPermission() {
        //Checking for camera
        if (ContextCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("vinhm", "request permission")
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.CAMERA), 1
            )
        } else {
            if (kv.decodeInt(SharedPreferencesUtils.GENDER, -1) == -1 || kv.decodeInt(
                    SharedPreferencesUtils.AGE, -1
                ) == -1 || kv.decodeInt(
                    SharedPreferencesUtils.HEIGHT, -1
                ) == -1 || kv.decodeInt(SharedPreferencesUtils.WEIGHT, -1) == -1
            ) {
                openInfoDialog()
            } else {
                val parent = parentFragment as FragmentWatcherContainer
                parent.replaceFragment(FragmentMeasureProgress())
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>, grantResults: IntArray
    ) {
        @Suppress("DEPRECATION") super.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(), Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (kv.decodeInt(SharedPreferencesUtils.GENDER, -1) == -1 || kv.decodeInt(
                        SharedPreferencesUtils.AGE, -1
                    ) == -1 || kv.decodeInt(
                        SharedPreferencesUtils.HEIGHT, -1
                    ) == -1 || kv.decodeInt(SharedPreferencesUtils.WEIGHT, -1) == -1
                ) {
                    openInfoDialog()
                } else {
                    val parent = parentFragment as FragmentWatcherContainer
                    parent.replaceFragment(FragmentMeasureProgress())
                }
            }
        }
    }

    private fun showMenuGender(view: View, tv_gender: TextView) {
        val menu = PopupMenu(requireActivity(), view)
        menu.menuInflater.inflate(R.menu.menu_gender, menu.menu)
        menu.setOnMenuItemClickListener(object : OnMenuItemClickListener {
            override fun onMenuItemClick(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.male -> {
                        tv_gender.setText(R.string.male)
                        return true
                    }
                    R.id.female -> {
                        tv_gender.setText(R.string.female)
                        return true
                    }
                    R.id.unknown -> {
                        tv_gender.setText(R.string.unknown)
                        return true
                    }
                }
                return false
            }
        })
        menu.show()
    }
}