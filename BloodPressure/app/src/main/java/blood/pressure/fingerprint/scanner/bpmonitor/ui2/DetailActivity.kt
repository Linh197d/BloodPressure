package blood.pressure.fingerprint.scanner.bpmonitor.ui2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.SubCategoryAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.ActivityInfoDetailBinding
import blood.pressure.fingerprint.scanner.bpmonitor.entities.MealResponse
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.GetDataService
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode
import blood.pressure.fingerprint.scanner.bpmonitor.retofitclient.RetrofitClientInstance
import blood.pressure.fingerprint.scanner.bpmonitor.util.TranslateAPI
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : BaseActivity() {
    var btnYoutube : Button? = null
    var imgToolbarBtnBack : ImageButton? = null
    var tvCategory : TextView?=null
    var progressbar : ProgressBar?=null
    var tvIngredients : TextView?=null
    var tvInstructions : TextView?=null
    var imgItem : RoundedImageView?=null
    var youtubeLink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        btnYoutube = findViewById(R.id.btnYoutube)
        imgToolbarBtnBack = findViewById(R.id.imgToolbarBtnBack)
        tvCategory= findViewById(R.id.tvCategory)
        tvIngredients= findViewById(R.id.tvIngredients)
        tvInstructions= findViewById(R.id.tvInstructions)
        progressbar= findViewById(R.id.progress_bar)
        imgItem= findViewById(R.id.imgItem)

        var id = intent.getStringExtra("id")
//        progressbar!!.visibility= View.VISIBLE
//        Handler().postDelayed(
//            {progressbar!!.visibility= View.GONE} ,2000
//        )

        getSpecificItem(id!!)

        imgToolbarBtnBack!!.setOnClickListener {
            finish()
        }

        btnYoutube!!.setOnClickListener {
            val uri = Uri.parse(youtubeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    fun getSpecificItem(id:String) {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getSpecificItem(id)
        call.enqueue(object : Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {

                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {

                Glide.with(this@DetailActivity).load(response.body()!!.mealsEntity[0].strmealthumb).into(imgItem!!)
//
                 translate(response.body()!!.mealsEntity[0].strmeal)
                var ingredient = "${response.body()!!.mealsEntity[0].stringredient1}      ${response.body()!!.mealsEntity[0].strmeasure1}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient2}      ${response.body()!!.mealsEntity[0].strmeasure2}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient3}      ${response.body()!!.mealsEntity[0].strmeasure3}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient4}      ${response.body()!!.mealsEntity[0].strmeasure4}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient5}      ${response.body()!!.mealsEntity[0].strmeasure5}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient6}      ${response.body()!!.mealsEntity[0].strmeasure6}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient7}      ${response.body()!!.mealsEntity[0].strmeasure7}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient8}      ${response.body()!!.mealsEntity[0].strmeasure8}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient9}      ${response.body()!!.mealsEntity[0].strmeasure9}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient10}      ${response.body()!!.mealsEntity[0].strmeasure10}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient11}      ${response.body()!!.mealsEntity[0].strmeasure11}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient12}      ${response.body()!!.mealsEntity[0].strmeasure12}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient13}      ${response.body()!!.mealsEntity[0].strmeasure13}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient14}      ${response.body()!!.mealsEntity[0].strmeasure14}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient15}      ${response.body()!!.mealsEntity[0].strmeasure15}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient16}      ${response.body()!!.mealsEntity[0].strmeasure16}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient17}      ${response.body()!!.mealsEntity[0].strmeasure17}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient18}      ${response.body()!!.mealsEntity[0].strmeasure18}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient19}      ${response.body()!!.mealsEntity[0].strmeasure19}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient20}      ${response.body()!!.mealsEntity[0].strmeasure20}\n"

                 translate1(ingredient)
                translate2(response.body()!!.mealsEntity[0].strinstructions)

                if (response.body()!!.mealsEntity[0].strsource != null){
                    youtubeLink = response.body()!!.mealsEntity[0].strsource
                }else{
                    btnYoutube!!.visibility = View.GONE
                }
            }

        })
    }
    fun translate(s:String){
        var textSt:String = ""
        val translate = TranslateAPI()

        translate.setOnTranslationCompleteListener(object :
            TranslateAPI.OnTranslationCompleteListener {
            override fun onStartTranslation() {
                // here you can perform initial work before translated the text like displaying progress bar
            }

            override fun onCompleted(text: String?) {
                // "text" variable will give you the translated text
                tvCategory!!.text =text
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
    fun translate1(s:String){
        var textSt:String = ""
        val translate = TranslateAPI()

        translate.setOnTranslationCompleteListener(object :
            TranslateAPI.OnTranslationCompleteListener {
            override fun onStartTranslation() {
                // here you can perform initial work before translated the text like displaying progress bar
            }

            override fun onCompleted(text: String?) {
                // "text" variable will give you the translated text
                tvIngredients!!.text =text
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
    fun translate2(s:String){
        var textSt:String = ""
        val translate = TranslateAPI()

        translate.setOnTranslationCompleteListener(object :
            TranslateAPI.OnTranslationCompleteListener {
            override fun onStartTranslation() {
                // here you can perform initial work before translated the text like displaying progress bar
            }

            override fun onCompleted(text: String?) {
                // "text" variable will give you the translated text
                tvInstructions!!.text =text
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