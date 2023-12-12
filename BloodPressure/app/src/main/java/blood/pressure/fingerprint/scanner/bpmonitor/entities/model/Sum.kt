package blood.pressure.fingerprint.scanner.bpmonitor.entities.model
import androidx.room.ColumnInfo

data class Sum(
    var date: String = "",
    @ColumnInfo(name = "Total")
    var total: Int = 0
)