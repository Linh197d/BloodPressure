package blood.pressure.fingerprint.scanner.bpmonitor.data

class Info(private val title: String, private val content: String) {

    fun getTitle(): String {
        return title
    }

    fun getContent(): String {
        return content
    }
}