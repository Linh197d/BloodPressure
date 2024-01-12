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
import blood.pressure.fingerprint.scanner.bpmonitor.entities.MealsItems
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil
import blood.pressure.fingerprint.scanner.bpmonitor.util.TranslateAPI
import com.bumptech.glide.Glide

class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx :Context? = null
    var arrSubCategory = ArrayList<MealsItems>()
    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvDishname: TextView = itemView.findViewById(R.id.tv_dish_name)
        var imgDish: ImageView = itemView.findViewById(R.id.img_dish)

    }

    fun setData(arrData : List<MealsItems>){
        arrSubCategory = arrData as ArrayList<MealsItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
         translate(arrSubCategory[position].strMeal,holder)
//        holder.tvDishname.text = stringConvert
        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.imgDish)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrSubCategory[position].idMeal)
        }
    }

    interface OnItemClickListener{
        fun onClicked(id:String)
    }
    fun translate(s:String,holder: SubCategoryAdapter.RecipeViewHolder){
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