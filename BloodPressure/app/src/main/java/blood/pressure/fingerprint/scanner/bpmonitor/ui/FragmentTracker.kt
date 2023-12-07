package blood.pressure.fingerprint.scanner.bpmonitor.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.App
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.ItemAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.data.ItemRoomDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.data.Items
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.Level
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentTrackerBinding
import blood.pressure.fingerprint.scanner.bpmonitor.util.Common
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.getCurrentLocale
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.selectedAnimate
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import ted.gun0912.rangebarchart.RangeBarChart
import ted.gun0912.rangebarchart.RangeBarData
import ted.gun0912.rangebarchart.RangeBarDataSet
import ted.gun0912.rangebarchart.RangeBarEntry
import java.util.*


@Suppress("DEPRECATION")
class FragmentTracker : Fragment(), IAdapter {

    private lateinit var binding: FragmentTrackerBinding
    private lateinit var database: ItemRoomDatabase
    private lateinit var list: MutableList<Items>
    lateinit var dates: MutableList<String>

    private lateinit var filter: ArrayList<String>

    private var index: Int = 0
    private var count: Int = 0
    private var displayX: Float = 8f

    var view = null
    private var mainActivity: Activity? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tracker,
            container,
            false
        )

        Common.pushEventAnalytics("tracker")

//        val rootView: View = inflater.inflate(R.layout.fragment_tracker, container, false)
//        val activity = rootView.getContext()
        val rootView = binding.root

        context?.theme?.applyStyle(R.style.EmptyChartTheme, true)
        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = ContextCompat.getDrawable(requireActivity(), R.drawable.rounded_text_view)
        layers[1] = ContextCompat.getDrawable(requireActivity(), R.drawable.bg_after_onclick)

        // Inflate the layout for this fragment
        binding.add.setOnClickListener {

            Common.pushEventAnalytics("add")
            if (Common.isConnected()) {

                activity?.startActivityForResult(
                    Intent(
                        activity,
                        AddNewRecordActivity::class.java
                    ).putExtra("action", "add"), 1
                )
                
            }else{
                Common.popUpNoInternet( requireActivity() )
            }

        }

        filter = ArrayList()
        filter.add(getString(R.string.min))
        filter.add(getString(R.string.average))
        filter.add(getString(R.string.average_24h))
        filter.add(getString(R.string.last))
        filter.add(getString(R.string.max))

        binding.previous.setOnClickListener { view ->
            selectedAnimate(view, layers)
            if (index == 0) {
                index = filter.size - 1
            } else {
                --index
            }
            binding.filter.text = filter[index]
            editInfo()
        }

        binding.next.setOnClickListener { view ->
            selectedAnimate(view, layers)
            if (index == filter.size - 1) {
                index = 0
            } else {
                ++index
            }
            binding.filter.text = filter[index]
            editInfo()
        }
        database = ItemRoomDatabase.getDatabase(requireContext())
        database.itemDAO().getAll().observe(requireActivity(), Observer {
            list = it as MutableList<Items>
            list.sortBy { items ->
                items.itemDate
            }
            editInfo()

            val layoutParams = binding.llFooter.layoutParams as RelativeLayout.LayoutParams
            if (list.isNotEmpty()) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                binding.llFooter.layoutParams = layoutParams
                binding.blurLayout.setBackgroundDrawable(null)
                binding.emptyRecommend.isGone = true
                binding.ivEmpty.isGone = true
                dates = arrayListOf()
                for (item in list) {
                    val s: String =
                        item.itemDate.split(" ")[0].split("-")[1] + "-" + item.itemDate.split(" ")[0].split(
                            "-"
                        )[2]
                    if (!dates.contains(s)) {
                        dates.add(s)
                    } else {
                        dates.add("")
                    }
                }
                binding.chart.setup()
                binding.chart.addData()
            } else {

//                val activity: Activity? = activity
//                initReview(activity)

                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL)
                binding.llFooter.layoutParams = layoutParams
                binding.scrollview1.isGone = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    binding.ivEmpty.alpha = 0.216f
                }
                binding.chart.setNoDataText("No Item")
                binding.chart.setNoDataTextColor(Color.parseColor("#FFFFFF"))
                val paint: Paint = binding.chart.getPaint(Chart.PAINT_INFO)
                paint.textSize = 40f
                binding.chart.invalidate()
            }

            list.reverse()
            if (activity != null) {
                binding.rcvData.layoutManager =
                    LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
                val adapter = ItemAdapter(requireActivity(), list, this)
                binding.rcvData.adapter = adapter
            }

        })

        return binding.root
    }

//    private fun initReview(activity: Activity?) {
//
//        val manager = ReviewManagerFactory.create(requireActivity())
//        val request = manager.requestReviewFlow()
//        request.addOnCompleteListener { task: Task<ReviewInfo?> ->
//            if (task.isSuccessful) {
//                Common.pushEventAnalytics("initReview")
//                val reviewInfo = task.result as ReviewInfo
//                val flow =
//                    manager.launchReviewFlow(
//                        requireActivity(), reviewInfo
//                    )
//                flow.addOnCompleteListener { task1: Task<Void?>? ->
//
//                }
//            }
//        }
//
//    }

    private fun editInfo() {
        when (index) {
            0 -> {
                binding.tvSystolic.text = list.minByOrNull { it.itemSYS }?.itemSYS.toString()
                binding.tvDiastolic.text = list.minByOrNull { it.itemDIA }?.itemDIA.toString()
                binding.tvPulse.text = list.minByOrNull { it.itemPulse }?.itemPulse.toString()
            }
            1 -> {
                binding.tvSystolic.text = list.map { it.itemSYS }.average().toInt().toString()
                binding.tvDiastolic.text = list.map { it.itemDIA }.average().toInt().toString()
                binding.tvPulse.text = list.map { it.itemPulse }.average().toInt().toString()
            }
            2 -> {
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val calendar =
                    Calendar.getInstance(getCurrentLocale( App.appContext ))
                binding.tvSystolic.text = list.filter {
                    it.itemDate.split(" ")[0] == "${calendar.get(Calendar.YEAR)}-" + String.format(
                        "%02d",
                        calendar.get(Calendar.MONTH) + 1
                    ) + "-" + String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))
                }.map { it.itemSYS }.average().toInt().toString()

                binding.tvDiastolic.text = list.filter {
                    it.itemDate.split(" ")[0] == "${calendar.get(Calendar.YEAR)}-" + String.format(
                        "%02d",
                        calendar.get(Calendar.MONTH) + 1
                    ) + "-" + String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))
                }.map { it.itemDIA }.average().toInt().toString()

                binding.tvPulse.text = list.filter {
                    it.itemDate.split(" ")[0] == "${calendar.get(Calendar.YEAR)}-" + String.format(
                        "%02d",
                        calendar.get(Calendar.MONTH) + 1
                    ) + "-" + String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))
                }.map { it.itemPulse }.average().toInt().toString()
            }
            3 -> {
                binding.tvSystolic.text = list[list.size - 1].itemSYS.toString()
                binding.tvDiastolic.text = list[list.size - 1].itemDIA.toString()
                binding.tvPulse.text = list[list.size - 1].itemPulse.toString()
            }
            4 -> {
                binding.tvSystolic.text = list.maxByOrNull { it.itemSYS }?.itemSYS.toString()
                binding.tvDiastolic.text = list.maxByOrNull { it.itemDIA }?.itemDIA.toString()
                binding.tvPulse.text = list.maxByOrNull { it.itemPulse }?.itemPulse.toString()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun RangeBarChart.setup() {
        setScaleEnabled(false)
        setDrawGridBackground(false)
        setBackgroundColor(Color.TRANSPARENT)
        description.isEnabled = false
        xAxis.apply {
            setDrawGridLines(false) //hide grid background
            setDrawAxisLine(false)  //hide x line of chart
            setDrawLabels(true)     //show x label
            setCenterAxisLabels(false)
            position = XAxis.XAxisPosition.BOTTOM
            textColor = Color.parseColor("#FFFFFF")
            textSize = 8f
            granularity = 1f
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String =
                    dates[convertValue(value)]
            }
        }

        axisLeft.apply {
            setDrawGridLines(true)
            setDrawAxisLine(false)
            enableGridDashedLine(10f, 10f, 0f)
            axisMinimum = 40f
            axisMaximum = 170f
            textColor = Color.parseColor("#FFFFFF")
            textSize = 13f
        }
        setVisibleXRange(1f, 10f)
        axisRight.isEnabled = false
        legend.isEnabled = false
        renderer = RangeBarChartRenderer(12f, this, animator, viewPortHandler)
        data = RangeBarData(createSet())
        zoom(list.size.toFloat().div(displayX), 1f, 1f, 1f)
        if (list.size > displayX) {
            moveViewToX(list.size.toFloat() - displayX)
        }
    }

    private fun convertValue(value: Float): Int {
        return if (value.toInt() < dates.size)
            value.toInt()
        else
            dates.size - 1
    }

    private fun createSet(): RangeBarDataSet = RangeBarDataSet(null, "RangeBar").apply {
        color = Color.parseColor("#2BDD9E")
        barWidth = if (list.size.toFloat() / displayX < 1f) {
            list.size.toFloat() / displayX * 0.75f
        } else {
            0.75f
        }
        isHighlightEnabled = true
        highLightColor = Color.CYAN
//        setGradientColor(
//            resources.getColor(R.color.color_hypotension),
//            resources.getColor(R.color.white)
//        )
        // minColor = Color.parseColor("#22BCFE")
        // maxColor = Color.parseColor("#FC351E")
        setDrawValues(false)
        valueTextSize = 12f
        valueTypeface = Typeface.DEFAULT_BOLD
        valueTextColor = Color.parseColor("#FFFFFF")
        valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = "${value.toInt()}"
        }
    }

    private fun getDataEntries(): List<RangeBarEntry> {
        val values = mutableListOf<RangeBarEntry>()
        //Log.i("bac", "${list.size}")
        for (item in list) {
            values.add(
                RangeBarEntry(
                    count.toFloat(),
                    item.itemDIA.toFloat(),
                    item.itemSYS.toFloat()
                )
            )
            count++
        }
        return values
    }

    private fun RangeBarChart.addData() {
        val data = data ?: return
        val rangeBarDataSet = data.getDataSetByIndex(0) ?: return
        val values = getDataEntries()
        val colors = mutableListOf<Int>()
        for (item in list) {
            Log.d("colorEdge", item.itemLevel)
            when (item.itemLevel) {
                Level.Hypotension -> colors.add(
                    ContextCompat.getColor(
                        App.appContext,
                        R.color.color_hypotension
                    )
                )
                Level.Normal -> colors.add(
                    ContextCompat.getColor(
                        App.appContext,
                        R.color.color_normal
                    )
                )
                Level.Elevated -> colors.add(
                    ContextCompat.getColor(
                        App.appContext,
                        R.color.color_elevated
                    )
                )
                Level.Hypertension_1 -> colors.add(
                    ContextCompat.getColor(
                        App.appContext,
                        R.color.color_hypertension_stage1
                    )
                )
                Level.Hypertension_2 -> colors.add(
                    ContextCompat.getColor(
                        App.appContext,
                        R.color.color_hypertension_stage2
                    )
                )
                Level.Hypertensive -> colors.add(
                    ContextCompat.getColor(
                        App.appContext,
                        R.color.color_hypertension
                    )
                )
            }
        }
        //rangeBarDataSet.isMinMaxEnabled = false
        rangeBarDataSet.isHighlightEnabled = false
        rangeBarDataSet.colors = colors
        rangeBarDataSet.values = values
        data.notifyDataChanged()
        notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {

        if (Common.isConnected()) {

            val intent = Intent(activity, AddNewRecordActivity::class.java)
            intent.putExtra("action", "edit")
            intent.putExtra("id", list[position].id)
            Log.i("bac", list[position].toString())
            activity?.startActivityForResult(intent, 1)

        }else{
            Common.popUpNoInternet( requireActivity() )
        }


//        if (interstitialAdApplovin != null && interstitialAdApplovin!!.isReady()) {
//            Log.e("AppLovin", "showAdsInterstitiaAd applovin")
//            AppLovin.getInstance().forceShowInterstitial(
//                requireActivity(),
//                interstitialAdApplovin,
//                object : AdCallback() {
//                    override fun onAdClosed() {
//                        Log.e("AppLovin", "showAdsInterstitiaAd applovin onAdClosed")
//                        // next action here
//                        Common.pushEventAnalytics("ad_interstitial_close")
//                        Common.pushEventAnalytics("ad_interstitial_close_applovin")
//
//                        val intent = Intent(activity, AddNewRecordActivity::class.java)
//                        intent.putExtra("action", "edit")
//                        intent.putExtra("id", list[position].id)
//                        Log.i("bac", list[position].toString())
//                        activity?.startActivityForResult(intent, 1)
//
//                        Common.loadAdsinterstitialApplovin( requireActivity() )
//                    }
//
//                    override fun onAdClicked() {
//                        super.onAdClicked()
//                    }
//                }, false
//            )
//        } else {
//
//            Common.loadAdsinterstitialApplovin( requireActivity() )
//
//            Admob.getInstance().forceShowInterstitial(
//                requireActivity(),
//                App.mInterstitialAd,
//                object : AdCallback() {
//
//                    override fun onNextAction() {
//                        val intent = Intent(activity, AddNewRecordActivity::class.java)
//                        intent.putExtra("action", "edit")
//                        intent.putExtra("id", list[position].id)
//                        Log.i("bac", list[position].toString())
//                        activity?.startActivityForResult(intent, 1)
//                    }
//
//                    override fun onAdClosed() {
//                        Common.pushEventAnalytics("ad_interstitial_close")
//                        Common.pushEventAnalytics("ad_interstitial_close_admob")
//
//                        val intent = Intent(activity, AddNewRecordActivity::class.java)
//                        intent.putExtra("action", "edit")
//                        intent.putExtra("id", list[position].id)
//                        Log.i("bac", list[position].toString())
//                        activity?.startActivityForResult(intent, 1)
//
//                        Common.loadAdInterstial(requireActivity())
//                    }
//                })
//        }



    }

    override fun onItemClick2(value: String) {

    }
}