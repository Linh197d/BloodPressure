package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.App.Companion.kv
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.LanguageAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode.Companion.languages
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityLanguageBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import java.util.*

class LanguageActivity : AppCompatActivity(), IAdapter {
    private var _binding: ActivityLanguageBinding? = null
    private val binding get() = _binding!!
    private var mCurrentLocale: Locale? = null
    private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()
    override fun getDelegate() = localeDelegate.getAppCompatDelegate(super.getDelegate())

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeDelegate.onCreate(this)

        _binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Common.pushEventAnalytics("language")

        MyUtil.setStatusBar(this)

        binding.rvLanguage.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = LanguageAdapter(this, languages, this)
        binding.rvLanguage.adapter = adapter

        binding.backLanguage.setOnClickListener { view ->
            val layers = arrayOfNulls<Drawable>(2)
            layers[0] = ContextCompat.getDrawable(this, R.drawable.rounded_text_view)
            layers[1] = ContextCompat.getDrawable(this, R.drawable.bg_after_onclick)
            MyUtil.selectedAnimate(view, layers)
            finish()
        }
    }

    override fun onItemClick(position: Int) {

    }

    override fun onItemClick2(value: String) {


        kv.encode(SharedPreferencesUtils.KEY_LOCALE_LANGUAGE, LanguageCode.getLanguageCode(value))

        startActivity(Intent(this, SplashScreen::class.java))
        finish()

    }

    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        val context = super.createConfigurationContext(overrideConfiguration)
        return LocaleHelper.onAttach(context)
    }

    override fun getApplicationContext(): Context =
        localeDelegate.getApplicationContext(super.getApplicationContext())

    open fun updateLocale(locale: Locale) {
        localeDelegate.setLocale(this, locale)
    }

}