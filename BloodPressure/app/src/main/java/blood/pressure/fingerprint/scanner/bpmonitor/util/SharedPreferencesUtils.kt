package blood.pressure.fingerprint.scanner.bpmonitor.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import blood.pressure.fingerprint.scanner.bpmonitor.R

class SharedPreferencesUtils(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("BloodPressure", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()
    val context = context

    companion object {
        const val INSTALL_FOR_THE_FIRST_TIME: String = "install"
        const val FIRST_SYNC: String = "sync"
        const val KEY_NOTE: String = "note"
        const val KEY_LAST_SYNC: String = "last sync"
        const val KEY_LOCALE_LANGUAGE: String = "language"
        const val GENDER: String = "gender"
        const val AGE: String = "age"
        const val HEIGHT: String = "height"
        const val WEIGHT: String = "weight"
    }

    fun putNoteValue(notes: Set<String>) {
        editor.putStringSet(KEY_NOTE, notes)
        editor.apply()
    }

    fun getNoteValue(): MutableSet<String>? {
        return sharedPreferences.getStringSet(KEY_NOTE, null)
    }

    fun putBooleanValue(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getStringValue(key: String?): String {
        return sharedPreferences.getString(key, context.getString(R.string.not_sync)).toString()
    }

    fun putStringValue(key: String?, value: String) {
        Log.d("bac", "put reference: key/$key, value/$value")
        editor.putString(key, value)
        editor.apply()
    }

    fun getBooleanValue(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}