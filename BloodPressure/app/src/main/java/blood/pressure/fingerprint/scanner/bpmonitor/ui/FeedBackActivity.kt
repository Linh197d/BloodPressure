package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityFeedBackBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.setStatusBar
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity

class FeedBackActivity : LocaleAwareCompatActivity() {
    private var _binding: ActivityFeedBackBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBar(this)

        binding.sendFeedback.isFocusable = false
        binding.sendFeedback.isClickable = false

        binding.feedbackContent.isFocusable = true
        binding.feedbackContent.isFocusableInTouchMode = true
        binding.feedbackContent.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        @Suppress("DEPRECATION")
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        binding.backFeedback.setOnClickListener { view ->
            val layers = arrayOfNulls<Drawable>(2)
            layers[0] = ContextCompat.getDrawable(this, R.drawable.rounded_text_view)
            layers[1] = ContextCompat.getDrawable(this,R.drawable.bg_after_onclick)
            MyUtil.selectedAnimate(view, layers)
            try {
                val imm1 = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm1.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            } catch (e: Exception) {
                // TODO: handle exception
            }
            finish()
        }

        binding.feedbackContent.doAfterTextChanged {
            if (binding.feedbackContent.text.isBlank()) {
                binding.sendFeedback.setTextColor(ContextCompat.getColor(this, R.color.color_note))
                binding.sendFeedback.isFocusable = false
                binding.sendFeedback.isClickable = false
            } else {
                binding.sendFeedback.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.sendFeedback.isFocusable = true
                binding.sendFeedback.isClickable = true
            }
        }

        binding.sendFeedback.setOnClickListener {
            if (binding.feedbackContent.text.isNotBlank()) {
                //todo send feedback

                try {
                    val imm2 = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm2.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    // TODO: handle exception
                }
                finish()
            }
        }


    }

    override fun onBackPressed() {
        try {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }
        super.onBackPressed()
    }
}