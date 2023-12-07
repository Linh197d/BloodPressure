package blood.pressure.fingerprint.scanner.bpmonitor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ItemDAO
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.NoteDAO

@Database(entities = [Items::class, Note::class], version = 2, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun itemDAO(): ItemDAO
    abstract fun noteDAO(): NoteDAO

    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}