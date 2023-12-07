package blood.pressure.fingerprint.scanner.bpmonitor.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "blood.pressure.fingerprint.scanner.bpmonitor.entities.Recipes")
data class Recipes(
        @PrimaryKey(autoGenerate = true)
        var id:Int,

        @ColumnInfo(name = "dishName")
        var dishName:String
) : Serializable