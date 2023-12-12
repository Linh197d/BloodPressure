/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package blood.pressure.fingerprint.scanner.bpmonitor

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import blood.pressure.fingerprint.scanner.bpmonitor.data.ItemRoomDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.ui.SplashScreen
import com.tencent.mmkv.MMKV
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperApplicationDelegate
import java.util.*


class App : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
    private var mCurrentLocale: Locale? = null
    lateinit var appOpenManager: ApplovinAppOpenManager

    private val localeAppDelegate = LocaleHelperApplicationDelegate()

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()


        if (App.instance == null) App.instance = this

        appContext = applicationContext

        MMKV.initialize(this)
        kv = MMKV.defaultMMKV()

        mCurrentLocale = appContext.resources.configuration.locale
        Log.d(
            "translate mCurrentLocale",
            mCurrentLocale.toString()
        )

//        AppLovinSdk.getInstance( this ).initializeSdk {
//            run {
        appOpenManager = ApplovinAppOpenManager(applicationContext)

//            }
//        }


    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeAppDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAppDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context =
        LocaleHelper.onAttach(super.getApplicationContext())


    companion object {
        lateinit var appContext: Context

        lateinit var kv: MMKV

        var is_open_app_from_noti = false
        private var instance: App? = null
        fun getInstance(): App? {
            return instance
        }
    }
}

class ApplovinAppOpenManager(applicationContext: Context?) : LifecycleObserver {
    private lateinit var context: Context
    private var loadTime: Long = 0

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        context = applicationContext!!
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    private var currentActivity: Activity? = null
    private var disabledAppOpenList: List<Class<*>>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onResume() {

        disabledAppOpenList = ArrayList<Class<*>>()
        (disabledAppOpenList as ArrayList<Class<*>>).add(SplashScreen::class.java)


        val am: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val cn: ComponentName? = am.getRunningTasks(1)[0].topActivity

        for (activity in disabledAppOpenList as ArrayList<Class<*>>) {
//            Log.e("appOpenAd", "activity " + activity.name)
            if (cn != null) {
//                Log.e("appOpenAd", "currentActivity " + cn.className)

                if (activity.name == cn.className) {
//                    Log.e("appOpenAd", "onStart: activity is disabled")
                    return
                }

            }

        }

    }


}




