package blood.pressure.fingerprint.scanner.bpmonitor.ui2.uiWater.food

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blood.pressure.fingerprint.scanner.bpmonitor.R
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.MainCategoryAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.SubCategoryAdapter
import blood.pressure.fingerprint.scanner.bpmonitor.database.RecipeDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.databinding.FragmentFoodBinding
import blood.pressure.fingerprint.scanner.bpmonitor.entities.CategoryItems
import blood.pressure.fingerprint.scanner.bpmonitor.entities.MealsItems
import blood.pressure.fingerprint.scanner.bpmonitor.ui2.DetailActivity
import kotlinx.coroutines.launch

class FoodFragment : Fragment() {

    companion object {
        fun newInstance() =
            FoodFragment()
    }
    private val COUNTRIES = arrayOf(
        "Beef", "Chicken", "Dessert", "Lamb", "Miscellaneous","Pasta","Pork","Seafood","Side","Starter"
        ,"Vegan","Vegetarian","Breakfast","Foat"
    )

    private lateinit var foodViewModel: FoodViewModel
    private lateinit var binding: FragmentFoodBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food, container, false)

        getDataFromDb()
        setSearchFood()

        // This disables back button
        mainCategoryAdapter.setClickListener(onCLicked)
        subCategoryAdapter.setClickListener(onCLickedSubItem)
        return binding.root
    }

    private fun setSearchFood() {
        // search food
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_dropdown_item_1line, COUNTRIES
        )
        binding.autoText.setAdapter(adapter)
        binding.search.setOnClickListener {
            getMealDataFromDb(binding.autoText.text.toString())
        }
    }

    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()


    private val onCLicked  = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        requireActivity().lifecycleScope.launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(requireActivity()).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()

                getMealDataFromDb(arrMainCategory[0].strcategory)
                mainCategoryAdapter.setData(arrMainCategory)
                binding.rvMainCategory.layoutManager = LinearLayoutManager(requireActivity(),
                    LinearLayoutManager.HORIZONTAL,false)
                binding.rvMainCategory.adapter = mainCategoryAdapter
            }


        }
    }

    private fun getMealDataFromDb(categoryName:String){
        binding.tvCategory.text = "$categoryName Category"
        requireActivity().lifecycleScope.launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(requireActivity()).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrSubCategory)
                binding.rvSubCategory.layoutManager = LinearLayoutManager(requireActivity(),
                    LinearLayoutManager.HORIZONTAL,false)
                binding.rvSubCategory.adapter = subCategoryAdapter
            }


        }
    }




}