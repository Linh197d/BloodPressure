package blood.pressure.fingerprint.scanner.bpmonitor.interfaces

interface Level {
    companion object {

        const val Hypotension: String = "Huyết áp thấp"
        const val Normal: String = "Bình thường"
        const val Elevated: String = "Hơi cao"
        const val Hypertension_1: String = "Huyết áp cao giai đoạn 1"
        const val Hypertension_2: String = "Huyết áp cao giai đoạn 2"
        const val Hypertensive: String = "Huyết áp cao nghiêm trọng"
    }
}