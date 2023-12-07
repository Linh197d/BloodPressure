package blood.pressure.fingerprint.scanner.bpmonitor.data

class Type(private val color: Int, private val name: String, private val detail: String) {

    fun getColor(): Int {
        return color
    }

    fun getName(): String {
        return name
    }

    fun getDetail(): String {
        return detail
    }
}