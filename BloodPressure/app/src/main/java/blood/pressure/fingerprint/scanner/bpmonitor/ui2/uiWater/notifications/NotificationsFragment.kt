package blood.pressure.fingerprint.scanner.bpmonitor.ui2.uiWater.notifications
import blood.pressure.fingerprint.scanner.bpmonitor.util.utils.AlarmScheduler
import blood.pressure.fingerprint.scanner.bpmonitor.util.utils.AppDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.util.utils.DatabaseHelper
import blood.pressure.fingerprint.scanner.bpmonitor.util.utils.Repository
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentNotificationsBinding
import blood.pressure.fingerprint.scanner.bpmonitor.entities.model.Notification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsFragment : Fragment() {

    // TODO(Oncreate in icindekileri functionlara tasi ve asagiya gotur)

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dao = AppDatabase.getDatabase(container?.context).dao()

        val db by lazy { DatabaseHelper(container!!.context) }

        // Inflate view and obtain an instance of the binding class
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)

        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the VieWModel
        binding.notificationsViewModel = notificationsViewModel

        // Specify the current activity as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = this

        // notification permission switch check listener
        binding.notificationPermissionSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.notificationContainerTobeHidden.visibility =
                if (isChecked) View.VISIBLE else View.GONE
            notificationsViewModel.notificationPreferenceHandler(if (isChecked) 1 else 0)
        }

        binding.startingTimeSpinner.onItemSelectedListener = notificationsViewModel.spinnerListener
        binding.finishingTimeSpinner.onItemSelectedListener = notificationsViewModel.spinnerListener
        binding.intervalSpinner.onItemSelectedListener = notificationsViewModel.spinnerListener

        // array adapter for starting and finishing hours
        val startingHourArray = ArrayAdapter.createFromResource(
            container!!.context,
            R.array.starting_hours_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.startingTimeSpinner.adapter = adapter
        }

        // array adapter for starting and finishing hours
        val finishingHourArray = ArrayAdapter.createFromResource(
            container.context,
            R.array.finishing_hours_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.finishingTimeSpinner.adapter = adapter
        }

        // array adapter for interval
        val intevalHourArray = ArrayAdapter.createFromResource(
            container.context,
            R.array.interval_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.intervalSpinner.adapter = adapter
        }

        lifecycleScope.launch {
            val notificationInfo = Repository(dao).readNotData()

            if (notificationInfo == null) {
                Repository(dao).insertNotificationInfo(Notification())
            } else {

                withContext(Dispatchers.Main) {
                    binding.notificationContainerTobeHidden.visibility =
                        (if (notificationInfo.notificationPreference == 1) View.VISIBLE else View.GONE)

                    // starting time onCreate setting
                    binding.notificationPermissionSwitch.isChecked =
                        notificationInfo.notificationPreference == 1

                    val editedStartingTime = if (notificationInfo.startingTime < 10) {
                        "0" + notificationInfo.startingTime.toString() + ":00"
                    } else {
                        notificationInfo.startingTime.toString() + ":00"
                    }

                    binding.startingTimeSpinner.setSelection(
                        startingHourArray.getPosition(editedStartingTime)
                    )

                    // finishing time onCreate setting
                    val editedFinishingTime = if (notificationInfo.finishingTime < 10) {
                        "0" + notificationInfo.finishingTime.toString() + ":00"
                    } else {
                        notificationInfo.finishingTime.toString() + ":00"
                    }

                    binding.finishingTimeSpinner.setSelection(
                        finishingHourArray.getPosition(editedFinishingTime)
                    )

                    // interval time onCreate setting
                    val intervalTime =
                        notificationInfo.interval.toString() + if (notificationInfo.interval == 1) " hour" else " hours"
                    binding.intervalSpinner.setSelection(intevalHourArray.getPosition(intervalTime))

                }

            }

        }


        // create channel function is called for notifications
        createChannel(
            channelId = getString(R.string.water_notification_channel_id),
            channelName = getString(R.string.water_notification_channel_name)
        )

        notificationsViewModel.notPreference.observe(viewLifecycleOwner, Observer { newPref ->
            val notTimes = notificationsViewModel.notificationTimes.value
            if (newPref == 1) {
                AlarmScheduler.scheduleAlarm(container.context, notTimes!!)
            } else {
                AlarmScheduler.cancelAlarm(container.context)
            }
        })

        return binding.root
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = R.color.colorPrimary
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Time to drink water"

            val notificationManager =
                requireActivity().getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
