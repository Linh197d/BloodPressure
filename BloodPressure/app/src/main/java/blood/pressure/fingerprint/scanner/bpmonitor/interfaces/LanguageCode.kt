package blood.pressure.fingerprint.scanner.bpmonitor.interfaces

interface LanguageCode {
    companion object {
        const val English: String = "en"
        const val Vietnamese: String = "vi"


        val languages: MutableList<String> = mutableListOf(
            "English",
            "Tiếng việt"
        )

        fun getLanguageCode(language: String): String {
            @Suppress("UNUSED_EXPRESSION")
            when (language) {
                "English" -> return English
                "Tiếng việt" -> return Vietnamese
            }
            return Vietnamese
        }
    }
}