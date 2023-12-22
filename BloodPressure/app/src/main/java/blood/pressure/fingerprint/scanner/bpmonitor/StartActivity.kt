package blood.pressure.fingerprint.scanner.bpmonitor

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import blood.pressure.fingerprint.scanner.bpmonitor.ui.SplashScreen
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.SplashActivity
import com.bumptech.glide.Glide

class StartActivity: AppCompatActivity() {
//    private lateinit var binding:StartActivity
    var btnBlood : TextView?=null
    var btnRecipe : TextView?=null
    var img : ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        btnBlood = findViewById(R.id.btnBlood)
        btnRecipe = findViewById(R.id.btnRecipe)
        img = findViewById(R.id.bg_main)
        initView()
        initData()
        initEvent()
    }



    private fun initView() {
        Glide.with(this@StartActivity).load(R.drawable.bg_main)
            .into(img!!)
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