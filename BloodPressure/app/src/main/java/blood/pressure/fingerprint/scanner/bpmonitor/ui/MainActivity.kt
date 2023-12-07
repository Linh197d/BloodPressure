package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityMainBinding
import blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi.BMIFragment
import blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi.FragmentBMIContainer
import blood.pressure.fingerprint.scanner.bpmonitor.ui.measure.FragmentWatcherContainer
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : LocaleAwareCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    lateinit var binding: ActivityMainBinding

    private val tracker: String = "tracker"
    private val measure: String = "measure"
    private val bmi: String = "bmi"
    private val info: String = "info"
    private val setting: String = "setting"
    private var current: String = tracker
    private var mCurrentLocale: Locale? = null

    companion object {
        val context = this
    }

//    private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()
//
//    override fun getDelegate() = localeDelegate.getAppCompatDelegate(super.getDelegate())
//
//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
//    }
//    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
//        val context = super.createConfigurationContext(overrideConfiguration)
//        return LocaleHelper.onAttach(context)
//    }
//
//    override fun getApplicationContext(): Context =
//        localeDelegate.getApplicationContext(super.getApplicationContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        localeDelegate.onCreate(this)

        setContentView(R.layout.activity_main)

        MyUtil.setStatusBar(this)

        if (Common.isConnected()) {

        } else {
            Common.popUpNoInternet(this)
        }

        mCurrentLocale = App.appContext.resources.configuration.locale
        Log.d(
            "translate Main mCurrentLocale",
            mCurrentLocale.toString()
        )

        loadFragment(FragmentTracker())
        bottomNav = findViewById(R.id.bottombar)
        bottomNav.selectedItemId = R.id.menu_tracker
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_info -> {
                    if (current != info) {
                        loadFragment(FragmentInfo())
                        current = info
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_measure -> {
                    if (current != measure) {
                        loadFragment(FragmentWatcherContainer())
                        current = measure
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_bmi -> {
                    if (current != bmi) {
                        loadFragment(FragmentBMIContainer())
                        current = bmi
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_tracker -> {
                    if (current != tracker) {
                        loadFragment(FragmentTracker())
                        current = tracker
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    if (current != setting) {
                        loadFragment(FragmentSettings())
                        current = setting
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

    }

//    private fun initReview(activity: Activity?) {
//
//        val manager = ReviewManagerFactory.create(this)
//        val request = manager.requestReviewFlow()
//        request.addOnCompleteListener { task: Task<ReviewInfo?> ->
//            if (task.isSuccessful) {
//                Common.pushEventAnalytics("initReview")
//                val reviewInfo = task.result as ReviewInfo
//                val flow =
//                    manager.launchReviewFlow(
//                        this, reviewInfo
//                    )
//                flow.addOnCompleteListener {
//
//                }
//            }
//        }
//
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Log.i("bacdz", "reload tracker access")
            loadFragment(FragmentTracker())
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Log.i("bacdz", "reload bmi")
            loadFragment(BMIFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.my_fragment, fragment)
        // transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, BeforeExitActivity::class.java))
        super.onBackPressed()
        //super.onBackPressed()
        finish()
    }
}