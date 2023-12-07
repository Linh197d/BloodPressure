package blood.pressure.fingerprint.scanner.bpmonitor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil.Companion.convertLanguage

@Suppress("DEPRECATION")
class LanguageAdapter(
    private var context: Context,
    private var listLanguage: List<String>,
    private var adapter: IAdapter
) :
    RecyclerView.Adapter<LanguageAdapter.ViewHolderItem>() {

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val language: TextView = itemView.findViewById(R.id.tv_language_item)
        val selected: ImageView = itemView.findViewById(R.id.selected_language)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderItem {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.language_item, parent, false)
        return ViewHolderItem(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val language: String = listLanguage[position]

        holder.language.text = language
        val configuration: Configuration = context.resources.configuration
        holder.selected.isGone = MyUtil.convertShortLanguage(configuration.locales.toLanguageTags().split("-")[0]) != language
        if (TextUtilsCompat.getLayoutDirectionFromLocale(convertLanguage(listLanguage[position])) == ViewCompat.LAYOUT_DIRECTION_LTR) {
            holder.language.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else {
            holder.language.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
        holder.itemView.setOnClickListener {
            if (MyUtil.convertShortLanguage(configuration.locales.toLanguageTags().split("-")[0]) != language) {
                adapter.onItemClick2(language)
            }
        }
    }

    override fun getItemCount(): Int {
        return listLanguage.size
    }
}