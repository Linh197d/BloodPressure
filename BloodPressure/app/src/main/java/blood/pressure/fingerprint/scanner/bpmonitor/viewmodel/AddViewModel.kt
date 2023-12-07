package blood.pressure.fingerprint.scanner.bpmonitor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.ItemDAO
import blood.pressure.fingerprint.scanner.bpmonitor.data.Items
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val itemDao: ItemDAO) : ViewModel() {

    val item: MutableLiveData<Items> = MutableLiveData()
    private fun insertItem(item: Items) {
        // viewModelScope để quản lý vòng đời ViewModel, nó sẽ dừng Coroutine con khi ViewModel dừng
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.insert(item)
        }
    }

    private fun deleteItemByID(itemID: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.deleteById(itemID)
        }
    }

    private fun updateItem(item: Items) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("bac", "update item")
            itemDao.update(item)
        }
    }

    fun getItemById(itemId: Int) {
        viewModelScope.launch {
            itemDao.getItem(itemId).collect {
                item.postValue(it)
            }
        }
    }

    private fun getNewItemEntry(
        itemSystolic: Int,
        itemDiastolic: Int,
        itemPulse: Int,
        itemLevel: String,
        itemNote: String,
        itemDate: String
    ): Items {
        return Items(
            itemSYS = itemSystolic,
            itemDIA = itemDiastolic,
            itemPulse = itemPulse,
            itemLevel = itemLevel,
            itemNote = itemNote,
            itemDate = itemDate
        )
    }

    fun addNewItem(
        itemSystolic: Int,
        itemDiastolic: Int,
        itemPulse: Int,
        itemLevel: String,
        itemNote: String,
        itemDate: String
    ) {
        val newItem =
            getNewItemEntry(itemSystolic, itemDiastolic, itemPulse, itemLevel, itemNote, itemDate)
        insertItem(newItem)
    }

    fun updateNewItem(
        id: Int,
        itemSystolic: Int,
        itemDiastolic: Int,
        itemPulse: Int,
        itemLevel: String,
        itemNote: String,
        itemDate: String
    ) {
        val newItem =
            Items(id, itemSystolic, itemDiastolic, itemPulse, itemLevel, itemNote, itemDate)
        updateItem(newItem)
    }

    fun isEntryValid(
        itemSystolic: Int,
        itemDiastolic: Int,
        itemPulse: Int,
        itemLevel: String,
        itemNote: String,
        itemDate: String
    ): Boolean {

        return true
    }

    fun removeItemByID(id: Int) {
        deleteItemByID(id)
    }

}

class AddViewModelFactory(private val itemDao: ItemDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddViewModel(itemDao) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}