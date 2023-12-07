package blood.pressure.fingerprint.scanner.bpmonitor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ItemType

@Suppress("DEPRECATION")
class NoteAdapter(
    private var context: Context,
    private var listNote: List<String>,
    private var adapter: IAdapter
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList: MutableList<String> = mutableListOf()

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNote: TextView = itemView.findViewById(R.id.tv_note_item)
    }

    class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeader: TextView = itemView.findViewById(R.id.tv_note_item_header)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == ItemType.HEADER) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item_header, parent, false)
            return ViewHolderHeader(view)
        }
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolderItem(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note: String = listNote[position]
        if (holder is ViewHolderHeader) {
            holder.tvHeader.text =
                context.getString(R.string.edit)
            holder.tvHeader.setOnClickListener {
                adapter.onItemClick(position)
            }
        } else if (holder is ViewHolderItem) {
            holder.tvNote.text = note
            if (mList.contains(note)) {
                holder.tvNote.setBackgroundResource(R.drawable.rounded_text_view_2)
            } else {
                holder.tvNote.setBackgroundResource(R.drawable.rounded_text_view)
            }

            holder.tvNote.setOnClickListener {
                if (mList.contains(note)) {
                    mList.remove(note)
                    selectedAnimate(holder.itemView, true)
                } else {
                    mList.add(note)
                    selectedAnimate(holder.itemView, false)
                }
                adapter.onItemClick2(note)
                //notifyDataSetChanged()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return ItemType.HEADER
        }
        return ItemType.ITEM
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun selectedAnimate(view: View, status: Boolean) {
        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = context.getDrawable(R.drawable.rounded_text_view)
        layers[1] = context.getDrawable(R.drawable.rounded_text_view_2)
        if (status) {
            layers[1] = context.getDrawable(R.drawable.rounded_text_view)
            layers[0] = context.getDrawable(R.drawable.rounded_text_view_2)
        }
        val transition = TransitionDrawable(layers)
        val sdk: Int = Build.VERSION.SDK_INT
        if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
            @Suppress("DEPRECATION")
            view.setBackgroundDrawable(transition)
        } else {
            view.background = transition
        }
        transition.startTransition(400)
    }

    fun getSelectedList(): MutableList<String> {
        return mList
    }

    fun setSelectedList(list: MutableList<String>) {
        mList.clear()
        mList.addAll(list)
    }
}