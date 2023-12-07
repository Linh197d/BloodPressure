package blood.pressure.fingerprint.scanner.bpmonitor.interfaces

interface Level {
    companion object {

        const val Hypotension: String = "Hypotension"
        const val Normal: String = "Normal"
        const val Elevated: String = "Elevated"
        const val Hypertension_1: String = "Hypertension Stage 1"
        const val Hypertension_2: String = "Hypertension Stage 2"
        const val Hypertensive: String = "Hypertensive"
    }
}