package blood.pressure.fingerprint.scanner.bpmonitor.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item")
data class Items(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "systolic")
    var itemSYS: Int,

    @ColumnInfo(name = "diastolic")
    var itemDIA: Int,

    @ColumnInfo(name = "pulse")
    var itemPulse: Int,

    @ColumnInfo(name = "level")
    var itemLevel: String,

    @ColumnInfo(name = "note")
    var itemNote: String,

    @ColumnInfo(name = "date")
    var itemDate: String
)