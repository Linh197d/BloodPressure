package blood.pressure.fingerprint.scanner.bpmonitor.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.entities.CategoryItems
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.TranslateAPI
import com.bumptech.glide.Glide

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx: Context? = null
    var arrMainCategory = ArrayList<CategoryItems>()
    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvDishname: TextView = itemView.findViewById(R.id.tv_dish_name)
        var imgDish: ImageView = itemView.findViewById(R.id.img_dish)

    }

    fun setData(arrData : List<CategoryItems>){
        arrMainCategory = arrData as ArrayList<CategoryItems>
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        Log.d("vinhm","main"+arrMainCategory[position].strcategory)
         translate(arrMainCategory[position].strcategory,holder)
//        holder.tvDishname.text = text.toString()

        Glide.with(ctx!!).load(arrMainCategory[position].strcategorythumb).into(holder.imgDish)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrMainCategory[position].strcategory)
        }
    }

    interface OnItemClickListener{
        fun onClicked(categoryName:String)
    }
    fun translate(s:String,holder: RecipeViewHolder){
        var textSt:String = ""
        val translate = TranslateAPI()

        translate.setOnTranslationCompleteListener(object :
            TranslateAPI.OnTranslationCompleteListener {
            override fun onStartTranslation() {
                // here you can perform initial work before translated the text like displaying progress bar
            }

            override fun onCompleted(text: String?) {
                // "text" variable will give you the translated text
                holder.tvDishname.text = text

            }

            override fun onError(e: Exception?) {
                Log.e("vinhm", "Translate Fail")
            }
        })
        translate.execute(
            s,
            LanguageCode.English,
            LanguageCode.Vietnamese
        )


    }
}