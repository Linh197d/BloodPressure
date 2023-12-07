package blood.pressure.fingerprint.scanner.bpmonitor.ui.measure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.Level

class FragmentWatcherContainer : Fragment() {

    var result = 60
    var sp = 90
    var dp = 60
    var level: String = Level.Normal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wacher_container, container, false)
        replaceFragment(FragmentStartMeasure())

        return view
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame_cloud_fragment, fragment)
        fragmentTransaction.commit()
    }
}