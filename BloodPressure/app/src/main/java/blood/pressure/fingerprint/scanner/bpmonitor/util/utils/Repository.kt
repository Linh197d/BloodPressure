package blood.pressure.fingerprint.scanner.bpmonitor.util.utils
import androidx.lifecycle.LiveData
import blood.pressure.fingerprint.scanner.bpmonitor.entities.model.Drink
import blood.pressure.fingerprint.scanner.bpmonitor.entities.model.Notification
import blood.pressure.fingerprint.scanner.bpmonitor.entities.model.Sum
import blood.pressure.fingerprint.scanner.bpmonitor.entities.model.User
import blood.pressure.fingerprint.scanner.bpmonitor.util.utils.Dao

class Repository(private val dao: Dao?) {

    suspend fun readDrinkDataDetailsSelectedDay(date: String): MutableList<Drink>? {
        return dao?.readDrinkDataDetailsSelectedDay(date)
    }

    // this function returning live data as opposed to below readUserData
    fun readData(): LiveData<User>? = dao?.readData()

    suspend fun readUserData(): User? = dao?.readUserData()

    suspend fun insertDrinkData(drink: Drink): Unit? = dao?.insertDrinkData(drink)

    suspend fun readDrinkSumData(): MutableList<Sum>? = dao?.readDrinkSumData()

    suspend fun insertData(user: User): Unit? = dao?.insertData(user)

    suspend fun updateUser(user: User): Unit? = dao?.updateUser(user)

    suspend fun readNotData(): Notification? = dao?.readNotData()

    suspend fun insertNotificationInfo(not: Notification): Unit? = dao?.insertNotificationInfo(not)

    suspend fun updateNotificationInfo(not: Notification): Unit? = dao?.updateNotificationInfo(not)

    suspend fun deleteSelectedDrinkData(drink: Drink): Unit? = dao?.deleteSelectedDrinkData(drink)

    suspend fun readDrinkDataDetailsDaySum() = dao?.readDrinkDataDetailsDaySum()

}
