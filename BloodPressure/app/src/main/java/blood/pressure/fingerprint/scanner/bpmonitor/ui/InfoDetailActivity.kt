package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.App.Companion.kv
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.InfoAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode
import blood.pressure.fingerprint.scanner.bpmonitor.data.InfoText
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityInfoDetailBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.isInternetConnection
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity


@Suppress("DEPRECATION")
class InfoDetailActivity : LocaleAwareCompatActivity() {
    private var _binding: ActivityInfoDetailBinding? = null
    private val binding get() = _binding!!
    var color: Int = 0
    private var isScroll = false
    private var isTranslate = false
    private lateinit var infoAdapter: InfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInfoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        if (Common.isConnected()) {

        }else{
            Common.popUpNoInternet(this)
        }

        Common.pushEventAnalytics("info_detail")

        initViews()
        binding.tvTranslate.animate().translationX(12f)

        binding.ivBackInfoDetail.setOnClickListener { view ->
            val layers = arrayOfNulls<Drawable>(2)
            layers[0] = ContextCompat.getDrawable(this, R.drawable.rounded_text_view)
            layers[1] = ContextCompat.getDrawable(this, R.drawable.bg_after_onclick)
            MyUtil.selectedAnimate(view, layers)
            val returnIntent = Intent()
            setResult(RESULT_OK, returnIntent)
            finish()
        }

        binding.tvTranslate.setOnClickListener {
            translate()
        }

        binding.rvInfoDetail.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((binding.rvInfoDetail.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0) {
                    isScroll = false
                    binding.tvTranslate.animate().translationX(12f)
                } else {
                    if (!isScroll) {
                        isScroll = true
                        binding.tvTranslate.animate().translationX(1000f)
                    }
                }
            }
        })
    }

    private fun initViews() {
//        val kv = MMKV.defaultMMKV()
        if (kv.decodeString(SharedPreferencesUtils.KEY_LOCALE_LANGUAGE) == LanguageCode.English) {
            binding.tvTranslate.isGone = true
        }
        if (isInternetConnection()) {
            isTranslate = true
            binding.tvTranslate.text = resources.getString(R.string.original_text)
        } else {
            isTranslate = false
            binding.tvTranslate.text = resources.getString(R.string.translate)
        }
        infoAdapter = InfoAdapter(this, InfoText.listKnow, isTranslate)
        when (intent.getStringExtra("content")) {
            FragmentInfo.INFO_KNOW -> infoAdapter =
                InfoAdapter(this, InfoText.listKnow, isTranslate)
            FragmentInfo.INFO_LEARN -> infoAdapter =
                InfoAdapter(this, InfoText.listLearn, isTranslate)
            FragmentInfo.INFO_FIND -> infoAdapter =
                InfoAdapter(this, InfoText.listFind, isTranslate)
            FragmentInfo.INFO_BREAK -> infoAdapter =
                InfoAdapter(this, InfoText.listBreak, isTranslate)
            FragmentInfo.INFO_TYPE -> infoAdapter =
                InfoAdapter(this, InfoText.listType, isTranslate)
            FragmentInfo.INFO_NOTICE -> infoAdapter =
                InfoAdapter(this, InfoText.listNotice, isTranslate)
            FragmentInfo.INFO_PROBLEM -> infoAdapter =
                InfoAdapter(this, InfoText.listProblem, isTranslate)
            FragmentInfo.INFO_UNDERSTAND -> infoAdapter =
                InfoAdapter(this, InfoText.listUnderstand, isTranslate)
            FragmentInfo.INFO_CONTROL -> infoAdapter =
                InfoAdapter(this, InfoText.listControl, isTranslate)
            FragmentInfo.INFO_DRUG_HYPER -> infoAdapter =
                InfoAdapter(this, InfoText.listDrugsHyper, isTranslate)
            FragmentInfo.INFO_LOWER -> infoAdapter =
                InfoAdapter(this, InfoText.listLower, isTranslate)
            FragmentInfo.INFO_DIAGNOSE -> infoAdapter =
                InfoAdapter(this, InfoText.listDiagnose, isTranslate)
            FragmentInfo.INFO_DRUG_HYPO -> infoAdapter =
                InfoAdapter(this, InfoText.listDrugsHypo, isTranslate)
            FragmentInfo.INFO_TIPS_HYPER -> infoAdapter =
                InfoAdapter(this, InfoText.listTipsHyper, isTranslate)
            FragmentInfo.INFO_TIPS_HYPO -> infoAdapter =
                InfoAdapter(this, InfoText.listTipsHypo, isTranslate)
        }

        val layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvInfoDetail.layoutManager = layoutManager
        binding.rvInfoDetail.adapter = infoAdapter
        if (intent.getIntExtra("type", -1) != -1) {
            binding.rvInfoDetail.smoothScrollToPosition(intent.getIntExtra("type", 1))
        }
        //set up view
        color = intent.getIntExtra("color", 0)
        binding.ivBackInfoDetail.backgroundTintList =
            ContextCompat.getColorStateList(this, color)
        @Suppress("DEPRECATION")
        MyUtil.selectedAnimate2(
            binding.ivBackInfoDetail,
            resources.getColor(R.color.grey),
            resources.getColor(color)
        )
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, color)
        if (color == R.color.color_elevated) {
            binding.ivBackInfoDetail.imageTintList =
                ContextCompat.getColorStateList(this, R.color.black)
            binding.tvInfoDetailTitle.setTextColor(resources.getColor(R.color.black))
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE
        }
        binding.viewBarInfoDetail.backgroundTintList = ContextCompat.getColorStateList(this, color)
        binding.tvInfoDetailTitle.text = intent.getStringExtra("title")
        binding.ivInfoDetailTitle.setImageDrawable(
            ContextCompat.getDrawable(this, intent.getIntExtra("image", 0))
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun translate() {
        if (!isInternetConnection()) {
            AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setCancelable(true)
                .setMessage(resources.getString(R.string.no_internet))
                .setNegativeButton(resources.getString(R.string.cancel)) { _, _ ->
                }
                .setPositiveButton(
                    R.string.retry
                ) { _, _ -> translate() }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        } else {
            if (!isTranslate) {
                isTranslate = true
                binding.tvTranslate.text = resources.getString(R.string.original_text)
            } else {
                isTranslate = false
                binding.tvTranslate.text = resources.getString(R.string.translate)
            }
            infoAdapter.isTranslate(isTranslate)
            infoAdapter.notifyDataSetChanged()
        }
    }
}