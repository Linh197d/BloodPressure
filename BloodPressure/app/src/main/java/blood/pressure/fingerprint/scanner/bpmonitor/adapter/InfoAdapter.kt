package blood.pressure.fingerprint.scanner.bpmonitor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.App.Companion.kv
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.data.Info
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ItemType
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils
import blood.pressure.fingerprint.scanner.bpmonitor.util.TranslateAPI

@Suppress("DEPRECATION")
class InfoAdapter(
    private var context: Context,
    private var listInfo: List<Info>,
    private var isTranslate: Boolean
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.info_item_title)
        var container: ConstraintLayout = itemView.findViewById(R.id.title_info_container)
        var content: TextView = itemView.findViewById(R.id.info_item_content)
        var loading: ProgressBar = itemView.findViewById(R.id.translating)
        var loadingText: TextView = itemView.findViewById(R.id.tv_translating)
    }

    class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == ItemType.HEADER) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.info_type_special, parent, false)
            return ViewHolderHeader(view)
        }
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return ViewHolderItem(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ObsoleteSdkInt")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val kv = MMKV.defaultMMKV()
        val currentLanguage =
            if (kv.decodeString(SharedPreferencesUtils.KEY_LOCALE_LANGUAGE) != null) {
                kv.decodeString(SharedPreferencesUtils.KEY_LOCALE_LANGUAGE)
            } else {
                ""
            }
        val info: Info = listInfo[position]
        if (holder is ViewHolderItem) {
            if (info.getTitle() == "") {
                holder.container.isGone = true
            } else {
                holder.container.isGone = false
                //holder.title.text = info.getTitle()
                if (currentLanguage == LanguageCode.English || !isTranslate) {
                    holder.title.text = info.getTitle()
                } else {
                    val translate = TranslateAPI()
                    translate.setOnTranslationCompleteListener(object :
                        TranslateAPI.OnTranslationCompleteListener {
                        override fun onStartTranslation() {
                            // here you can perform initial work before translated the text like displaying progress bar
                        }

                        override fun onCompleted(text: String?) {
                            // "text" variable will give you the translated text
                            holder.title.text = text
                        }

                        override fun onError(e: Exception?) {
                            Log.e("vinhm", "Translate Fail")
                        }
                    })
                    if (currentLanguage != null) {
                        Log.d("vinhm", "$currentLanguage adapter")
                    }
                    translate.execute(
                        info.getTitle(),
                        LanguageCode.English,
                        currentLanguage
                    )
                }
            }
            if (currentLanguage == LanguageCode.English || !isTranslate) {
                holder.loading.isGone = true
                holder.loadingText.isGone = true
                if (info.getContent().contains('<')) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.content.text =
                            Html.fromHtml(info.getContent(), Html.FROM_HTML_MODE_COMPACT)
                    } else
                        holder.content.text = Html.fromHtml(info.getContent())
                } else {
                    holder.content.text = info.getContent()
                }
            } else {
                holder.loading.isGone = false
                holder.loadingText.isGone = false
                val translate = TranslateAPI()
                translate.setOnTranslationCompleteListener(object :
                    TranslateAPI.OnTranslationCompleteListener {
                    override fun onStartTranslation() {
                        // here you can perform initial work before translated the text like displaying progress bar
                    }

                    override fun onCompleted(text: String?) {
                        // "text" variable will give you the translated text
                        //holder.content.text = text
                        if (info.getContent().contains('<')) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                holder.content.text =
                                    Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
                            } else
                                holder.content.text = Html.fromHtml(text)
                        } else {
                            holder.content.text = text
                        }
                        holder.loading.isGone = true
                        holder.loadingText.isGone = true
                    }

                    override fun onError(e: Exception?) {
                        Log.e("vinhm", "Translate Fail")
//                        holder.loading.isGone = true
//                        holder.loadingText.isGone = true
//                        if (info.getTitle()==""){
//                            holder.container.isGone = true
//                        }else{
//                            holder.container.isGone = false
//                            holder.title.text =info.getTitle()
//                        }
//                        if (info.getContent().contains('<')) {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                holder.content.text =
//                                    Html.fromHtml(info.getContent(), Html.FROM_HTML_MODE_COMPACT)
//                            } else
//                                holder.content.text = Html.fromHtml(info.getContent())
//                        } else {
//                            holder.content.text = info.getContent()
//                        }
//                        holder.loading.isGone = true
//                        holder.loadingText.isGone = true
                    }
                })
                translate.execute(info.getContent(), LanguageCode.English, currentLanguage)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (listInfo[position].getTitle() == "header") {
            return ItemType.HEADER
        }
        return ItemType.ITEM
    }

    override fun getItemCount(): Int {
        return listInfo.size
    }

    fun isTranslate(b: Boolean) {
        isTranslate = b
    }
}