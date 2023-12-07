package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.App.Companion.kv
import blood.pressure.fingerprint.scanner.bpmonitor.R.layout
import blood.pressure.fingerprint.scanner.bpmonitor.R.string
import blood.pressure.fingerprint.scanner.bpmonitor.data.ItemRoomDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.data.Note
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreen : LocaleAwareCompatActivity() {

    private lateinit var database: ItemRoomDatabase
    private val currentActivity: Activity? = null
    private val myApplication: Application? = null

    private var mCurrentLocale: Locale? = null

//    private lateinit var appOpenManager2: ApplovinAppOpenManagerSplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_sflash_screen)
        MyUtil.setStatusBar(this)

        initView()
    }

    private fun initView() {
        Handler().postDelayed({
            database = ItemRoomDatabase.getDatabase(this)
            database.noteDAO().getAll().observe(this, Observer {
                if (it.isNullOrEmpty()) {
                    insertNoteForFirstInstall()
                }
            })
            Log.d(
                "translate language",
                kv.decodeString(SharedPreferencesUtils.KEY_LOCALE_LANGUAGE).toString()
            )
            mCurrentLocale = App.appContext.resources.configuration.locale
            startMain()
            loadAOA()


        }, 3000)
    }


    private fun loadAOA() {

        Log.e("appOpenAd", "appOpenAd loadAOA")

        Common.pushEventAnalytics("internet")
        if (Common.isInternetConnected(this)) {
            Common.pushEventAnalytics("internet_on")
        } else {
            Common.pushEventAnalytics("internet_off")
        }

    }

    private fun startMain() {

        try {
            if (kv.decodeBool(SharedPreferencesUtils.INSTALL_FOR_THE_FIRST_TIME, true)) {
                startActivity(
                    Intent(
                        this, MainLanguageActivity::class.java
                    )
                )
            } else {
                startActivity(
                    Intent(
                        this, MainActivity::class.java
                    )
                )
            }

            finish()

        } catch (e: Exception) {

        }

    }

    private fun loadSplash() {

        Common.pushEventAnalytics("internet")
        if (Common.isInternetConnected(this)) {
            Common.pushEventAnalytics("internet_on")
        } else {
            Common.pushEventAnalytics("internet_off")
        }

    }

    private fun insertNoteForFirstInstall() {
        Log.i("bac", "insert database")
        CoroutineScope(Dispatchers.IO).launch {
            database.noteDAO().insert(Note(getString(string.left)))
            database.noteDAO().insert(Note(getString(string.right)))
            database.noteDAO().insert(Note(getString(string.after_medical)))
            database.noteDAO().insert(Note(getString(string.after_walking)))
            database.noteDAO().insert(Note(getString(string.before_meal)))
            database.noteDAO().insert(Note(getString(string.after_meal)))
            database.noteDAO().insert(Note(getString(string.sitting)))
            database.noteDAO().insert(Note(getString(string.lying)))
            database.noteDAO().insert(Note(getString(string.period)))
        }
    }


}



