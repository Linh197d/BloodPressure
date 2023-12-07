package blood.pressure.fingerprint.scanner.bpmonitor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey
    var name: String
)