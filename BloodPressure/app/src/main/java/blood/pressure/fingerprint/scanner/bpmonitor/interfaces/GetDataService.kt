package blood.pressure.fingerprint.scanner.bpmonitor.interfaces
import blood.pressure.fingerprint.scanner.bpmonitor.entities.Category
import blood.pressure.fingerprint.scanner.bpmonitor.entities.Meal
import blood.pressure.fingerprint.scanner.bpmonitor.entities.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>


}