package blood.pressure.fingerprint.scanner.bpmonitor.ui2
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityMainFoodBinding
import blood.pressure.fingerprint.scanner.bpmonitor.ui.FragmentInfo
import blood.pressure.fingerprint.scanner.bpmonitor.ui.FragmentSettings
import blood.pressure.fingerprint.scanner.bpmonitor.ui.FragmentTracker
import blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi.FragmentBMIContainer
import blood.pressure.fingerprint.scanner.bpmonitor.ui.measure.FragmentWatcherContainer
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.uiWater.dashboard.DashboardFragment
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.uiWater.food.FoodFragment
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.uiWater.history.HistoryFragment
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.uiWater.home.HomeFragment
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.uiWater.notifications.NotificationsFragment
import blood.pressure.fingerprint.scanner.bpmonitor.util.utils.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainFoodBinding
    private lateinit var bottomNav: BottomNavigationView
    private val home: String = "home"
    private val history: String = "history"
    private val food: String = "food"
    private val noti: String = "noti"
    private val setting: String = "setting"
    private var current: String = home
    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain the FirebaseAnalytics instance.

        database = AppDatabase.getDatabase(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_food)
        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.nav_view)
        bottomNav.selectedItemId = R.id.navigation_setting
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    if (current != home) {
                        loadFragment(HomeFragment())
                        current = home
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    if (current != noti) {
                        loadFragment(NotificationsFragment())
                        current = noti
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_history -> {
                    if (current != history) {
                        loadFragment(HistoryFragment())
                        current = history
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_food -> {
                    if (current != food) {
                        loadFragment(FoodFragment())
                        current = food
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    if (current != setting) {
                        loadFragment(DashboardFragment())
                        current = setting
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
//        val navController = findNavController(R.id.nav_host_fragment)

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
    public fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.my_fragment_food, fragment)
        // transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }
}
