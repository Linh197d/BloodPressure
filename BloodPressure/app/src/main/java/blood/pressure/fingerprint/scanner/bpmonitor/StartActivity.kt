package blood.pressure.fingerprint.scanner.bpmonitor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityMainBinding
import blood.pressure.fingerprint.scanner.bpmonitor.ui.BeforeExitActivity
import blood.pressure.fingerprint.scanner.bpmonitor.ui.FragmentInfo
import blood.pressure.fingerprint.scanner.bpmonitor.ui.FragmentSettings
import blood.pressure.fingerprint.scanner.bpmonitor.ui.FragmentTracker
import blood.pressure.fingerprint.scanner.bpmonitor.ui.SplashScreen
import blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi.BMIFragment
import blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi.FragmentBMIContainer
import blood.pressure.fingerprint.scanner.bpmonitor.ui.measure.FragmentWatcherContainer
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.HomeActivity
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.SplashActivity
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class StartActivity: AppCompatActivity() {
//    private lateinit var binding:StartActivity
    var btnBlood : LinearLayoutCompat?=null
    var btnRecipe : LinearLayoutCompat?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        btnBlood = findViewById(R.id.btnBlood)
        btnRecipe = findViewById(R.id.btnRecipe)
        initView()
        initData()
        initEvent()
    }



    private fun initView() {
    }
    private fun initData() {
    }

    private fun initEvent() {
        btnBlood!!.setOnClickListener {
            var intent = Intent(this@StartActivity, SplashScreen::class.java)
            startActivity(intent)
        }
        btnRecipe!!.setOnClickListener {
            var intent = Intent(this@StartActivity, SplashActivity::class.java)
            startActivity(intent)
        }
    }

}