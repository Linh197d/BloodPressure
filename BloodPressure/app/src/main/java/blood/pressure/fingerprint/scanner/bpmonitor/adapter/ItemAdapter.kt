package blood.pressure.fingerprint.scanner.bpmonitor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.data.Items
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.Level
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter

class ItemAdapter(
    private var context: Context,
    private var listItem: List<Items>,
    private var adapter: IAdapter
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSYS: TextView
        var tvDIA: TextView
        var tvPUL: TextView
        var tvLevel: TextView
        var tvNote: TextView
        var iv: ImageView

        init {
            tvSYS = itemView.findViewById(R.id.tv_data_sys)
            tvDIA = itemView.findViewById(R.id.tv_data_dia)
            tvPUL = itemView.findViewById(R.id.tv_titletime)
            tvLevel = itemView.findViewById(R.id.tv_title1)
            tvNote = itemView.findViewById(R.id.tv_notes)
            iv = itemView.findViewById(R.id.iv)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.tvSYS.text = item.itemSYS.toString()
        holder.tvDIA.text = item.itemDIA.toString()
        holder.tvPUL.text =
            item.itemDate.split("-")[1] + "-" + item.itemDate.split("-")[2] + ", " + item.itemPulse.toString() + " BPM"
        if (item.itemNote != "") {
            holder.tvNote.text = item.itemNote
            Log.d("note", item.itemNote)
            holder.tvNote.isGone = false
        } else {
            holder.tvNote.isGone = true
        }
        when (item.itemLevel) {
            Level.Hypotension -> {
                holder.iv.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_hypotension)
                holder.tvLevel.text = context.getString(R.string.hypotension)
            }
            Level.Normal -> {
                holder.iv.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_normal)
                holder.tvLevel.text = context.getString(R.string.normal)
            }
            Level.Elevated -> {
                holder.iv.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_elevated)
                holder.tvLevel.text = context.getString(R.string.elevated)
            }
            Level.Hypertension_1 -> {
                holder.iv.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_hypertension_stage1)
                holder.tvLevel.text = context.getString(R.string.hypertension_stage_1)
            }
            Level.Hypertension_2 -> {
                holder.iv.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_hypertension_stage2)
                holder.tvLevel.text = context.getString(R.string.hypertension_stage_2)
            }
            Level.Hypertensive -> {
                holder.iv.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.color_hypertension)
                holder.tvLevel.text = context.getString(R.string.hypertensive)
            }
        }
        holder.itemView.setOnClickListener {
            setSelectedItemAnimate(holder.itemView)
            adapter.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setSelectedItemAnimate(view: View) {
        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = context.getDrawable(R.drawable.bg_item_data_2)
        layers[1] = context.getDrawable(R.drawable.bg_item_data)
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
}