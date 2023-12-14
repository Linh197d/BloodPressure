package blood.pressure.fingerprint.scanner.bpmonitor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import blood.pressure.fingerprint.scanner.bpmonitor.ui.SplashScreen
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.SplashActivity

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