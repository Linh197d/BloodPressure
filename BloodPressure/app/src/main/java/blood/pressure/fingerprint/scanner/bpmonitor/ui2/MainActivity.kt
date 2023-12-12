package blood.pressure.fingerprint.scanner.bpmonitor.ui2
import AppDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityMainFoodBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainFoodBinding

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain the FirebaseAnalytics instance.

        database = AppDatabase.getDatabase(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_food)

        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home,
//                R.id.navigation_setting,
//                R.id.navigation_notifications,
//                R.id.navigation_history
//            )
//        )
//        Timber.plant(Timber.DebugTree())
        // Monitor launch times and interval from installation
//        RateThisApp.onCreate(this);
//        // If the condition is satisfied, "Rate this app" dialog will be shown
//        RateThisApp.showRateDialogIfNeeded(this);
        // Custom condition: 3 days and 5 launches
//        val config = RateThisApp.Config(3, 5)
//        RateThisApp.init(config)

//        binding.navView.setupWithNavController(navController)
    }
}
