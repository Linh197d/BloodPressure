package blood.pressure.fingerprint.scanner.bpmonitor.ui.bmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import blood.pressure.fingerprint.scanner.bpmonitor.R

class FragmentBMIContainer : Fragment() {

    var gender = 1
    var height = 160
    var weight = 50

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_b_m_i_container, container, false)

        replaceFragment(BMIFragment())

        return view
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame_cloud_fragment, fragment)
        fragmentTransaction.commit()
    }
}