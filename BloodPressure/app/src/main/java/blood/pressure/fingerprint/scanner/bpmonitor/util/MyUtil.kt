package blood.pressure.fingerprint.scanner.bpmonitor.util

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode
import com.zeugmasolutions.localehelper.Locales
import java.util.*

class MyUtil {
    companion object {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun selectedAnimate(view: View, layers: Array<Drawable?>) {
            val transition = TransitionDrawable(layers)
            val sdk: Int = Build.VERSION.SDK_INT
            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                @Suppress("DEPRECATION")
                view.setBackgroundDrawable(transition)
            } else {
                view.background = transition
            }
            transition.startTransition(300)
        }

        fun selectedAnimate2(view: View, from: Int, to: Int) {
            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), from, to)
            colorAnimation.duration = 300 // milliseconds
            colorAnimation.addUpdateListener { animator -> view.setBackgroundColor(animator.animatedValue as Int) }
            colorAnimation.start()
        }

        @SuppressLint("ObsoleteSdkInt")
        fun getCurrentLocale(context: Context): Locale? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales[0]
            } else {
                @Suppress("DEPRECATION")
                context.resources.configuration.locale
            }
        }

        fun setProgressDialog(context: Context, message: String): AlertDialog {
            val llPadding = 30
            val ll = LinearLayout(context)
            ll.orientation = LinearLayout.HORIZONTAL
            ll.setPadding(llPadding, llPadding, llPadding, llPadding)
            ll.gravity = Gravity.CENTER
            var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            llParam.gravity = Gravity.CENTER
            ll.layoutParams = llParam

            val progressBar = ProgressBar(context)
            progressBar.isIndeterminate = true
            progressBar.setPadding(0, 0, llPadding, 0)
            progressBar.layoutParams = llParam

            llParam = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            llParam.gravity = Gravity.CENTER
            val tvText = TextView(context)
            tvText.text = message
            tvText.setTextColor(Color.parseColor("#000000"))
            tvText.textSize = 20.toFloat()
            tvText.layoutParams = llParam

            ll.addView(progressBar)
            ll.addView(tvText)

            val builder = AlertDialog.Builder(context)
            builder.setCancelable(true)
            builder.setView(ll)

            val dialog = builder.create()
            val window = dialog.window
            if (window != null) {
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(dialog.window?.attributes)
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
                dialog.window?.attributes = layoutParams
            }
            return dialog
        }

        @Suppress("DEPRECATION")
        fun setStatusBar(context: Activity) {
            val window = context.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE
            window.statusBarColor = ContextCompat.getColor(context, R.color.color_background)
        }

        fun convertShortLanguage(language: String): String {
            @Suppress("UNUSED_EXPRESSION")
            when (language) {
                LanguageCode.English -> return "English"
                LanguageCode.Vietnamese -> return "Tiếng việt"
            }
            return ""
        }

        fun convertLanguage(language: String): Locale {
            @Suppress("UNUSED_EXPRESSION")
            when (language) {
                "English" -> return Locales.English
                "Tiếng việt" -> return Locales.Vietnamese
            }
            return Locales.English
        }

        @Suppress("DEPRECATION")
        @SuppressLint("MissingPermission")
        fun isInternetConnection(): Boolean {
            val cm =
                App.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    return true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    return true
                }
            }
            return false
        }
        fun translate(s:String):String{
            var textSt:String = ""
            val translate = TranslateAPI()

                translate.setOnTranslationCompleteListener(object :
                    TranslateAPI.OnTranslationCompleteListener {
                    override fun onStartTranslation() {
                        // here you can perform initial work before translated the text like displaying progress bar
                    }

                    override fun onCompleted(text: String?) {
                        // "text" variable will give you the translated text
                        textSt+=text
                    }

                    override fun onError(e: Exception?) {
                        Log.e("vinhm", "Translate Fail")
                    }
                })
            translate.execute(
                s,
                LanguageCode.English,
                LanguageCode.Vietnamese
            )
            return textSt


        }
    }
}