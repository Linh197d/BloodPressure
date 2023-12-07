package blood.pressure.fingerprint.scanner.bpmonitor.util

import android.app.Dialog
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import java.io.*
import java.util.*


object Common {

    @JvmStatic
    fun pushEventAnalytics(nameEvent: String?) {
        val params = Bundle()
    }


    //ads


    @JvmStatic
    fun loadAdInterstial(context: Context) {

    }

    @JvmStatic
    fun loadAdsinterstitialApplovin(context: Context) {

    }


    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork ?: return false
        } else {
            return true
        }
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun popUpNoInternet( context: Context ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.no_internet_pop_up)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //setup position of dialog
        val windowAttribute = window.attributes
        windowAttribute.gravity = Gravity.CENTER
        window.attributes = windowAttribute

        //initview
        val mCancel =
            window.findViewById<ImageView>(R.id.iv_cancel)
        val mTurnOn =
            window.findViewById<Button>(R.id.btn_turn_on_internet)


        //init event
        mTurnOn.setOnClickListener { view: View? ->
            //TODO
            dialog.dismiss()
        }
        mCancel.setOnClickListener { view: View? -> dialog.dismiss() }
        dialog.setCancelable(false)

        //show dialog
        dialog.show()
    }

    fun isConnected(): Boolean {
        var connected = false
        try {
            val cm = App.appContext.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            Log.e("Connectivity Exception", e.message!!)
        }
        return connected
    }

}