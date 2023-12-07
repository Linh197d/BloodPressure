package blood.pressure.fingerprint.scanner.bpmonitor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.data.Type
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ITypeAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ItemType

@Suppress("DEPRECATION")
class TypeAdapter(
    private var context: Context,
    private var listType: List<Type>,
    private var adapter: ITypeAdapter
) :
    RecyclerView.Adapter<TypeAdapter.ViewHolderItem>() {

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var color: View = itemView.findViewById(R.id.color_type)
        var name: TextView = itemView.findViewById(R.id.tv_type_name)
        var detail: TextView = itemView.findViewById(R.id.tv_type_detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderItem {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.type_item, parent, false)
        return ViewHolderItem(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val type: Type = listType[position]

        holder.name.text = type.getName()
        holder.detail.text = type.getDetail()
        holder.color.backgroundTintList = ContextCompat.getColorStateList(context, type.getColor())
        holder.itemView.setOnClickListener {
            adapter.onTypeClick(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return ItemType.ITEM
    }

    override fun getItemCount(): Int {
        return listType.size
    }
}