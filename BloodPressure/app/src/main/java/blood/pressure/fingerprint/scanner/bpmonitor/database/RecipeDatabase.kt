package blood.pressure.fingerprint.scanner.bpmonitor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import blood.pressure.fingerprint.scanner.bpmonitor.dao.RecipeDao
import blood.pressure.fingerprint.scanner.bpmonitor.entities.Category
import blood.pressure.fingerprint.scanner.bpmonitor.entities.CategoryItems
import blood.pressure.fingerprint.scanner.bpmonitor.entities.Meal
import blood.pressure.fingerprint.scanner.bpmonitor.entities.MealsItems
import blood.pressure.fingerprint.scanner.bpmonitor.entities.Recipes
import blood.pressure.fingerprint.scanner.bpmonitor.entities.converter.CategoryListConverter
import blood.pressure.fingerprint.scanner.bpmonitor.entities.converter.MealListConverter


@Database(
    entities = [Recipes::class, CategoryItems::class, Category::class, Meal::class, MealsItems::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(CategoryListConverter::class, MealListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {

    companion object {

        var recipesDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipesDatabase == null) {
                recipesDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}