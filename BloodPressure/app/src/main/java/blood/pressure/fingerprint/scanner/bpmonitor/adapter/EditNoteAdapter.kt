package blood.pressure.fingerprint.scanner.bpmonitor.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ItemType
import java.util.Collections

@Suppress("DEPRECATION")
class EditNoteAdapter(
    private var listNote: List<String>,
    private var adapter: IAdapter
) :
    RecyclerView.Adapter<EditNoteAdapter.ViewHolderItem>(),
    RecyclerItemMoveCallBack.RecyclerViewItemTouchHelperContract {

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNote: TextView = itemView.findViewById(R.id.tv_note_edit)
        var imageView: ImageView = itemView.findViewById(R.id.iv_delete_note)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderItem {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.edit_note_item, parent, false)
        return ViewHolderItem(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val note: String = listNote[position]

        holder.tvNote.text = note

        holder.imageView.setOnClickListener {
            adapter.onItemClick2(note)
            //notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return ItemType.ITEM
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onItemMoved(from: Int, to: Int) {
        if (from < to) {
            for (i in from until to - 1) {
                Collections.swap(listNote, i, i + 1)
            }
        } else {
            for (i in from downTo to + 1) {
                Collections.swap(listNote, i,i - 1)
            }
        }
        notifyItemMoved(from, to)
    }

    override fun onItemSelected(viewHolder: ViewHolderItem) {

    }

    override fun onItemClear(viewHolder: ViewHolderItem) {

    }
}