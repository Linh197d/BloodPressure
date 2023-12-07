package blood.pressure.fingerprint.scanner.bpmonitor.interfaces

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import blood.pressure.fingerprint.scanner.bpmonitor.data.Items
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Items)

    @Update
    suspend fun update(item: Items)

    @Delete
    suspend fun delete(item: Items)

    @Query("DELETE FROM item")
    fun deleteAll()

    @Query("DELETE FROM item WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM item Where id = :id")
    fun getItem(id: Int): Flow<Items>

    @Query("Select * from item")
    fun getAll(): LiveData<List<Items>>

    @Query("SELECT * FROM item")
    fun getAllCursorItem(): Cursor

    @Query("Select * from item")
    fun getListItems(): MutableList<Items>

}




