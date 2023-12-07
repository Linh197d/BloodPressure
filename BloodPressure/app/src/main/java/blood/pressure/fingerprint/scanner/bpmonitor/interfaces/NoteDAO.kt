package blood.pressure.fingerprint.scanner.bpmonitor.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import blood.pressure.fingerprint.scanner.bpmonitor.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM note WHERE name = :name")
    fun deleteByName(name: String)

    @Query("DELETE FROM note")
    fun deleteAll()

    @Query("SELECT * FROM note Where name = :name")
    fun getNote(name: String): Flow<Note>

    @Query("Select * from note")
    fun getAll(): LiveData<List<Note>>

    @Query("Select * from note")
    fun getListNote(): MutableList<Note>

}