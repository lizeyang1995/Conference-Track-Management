import java.io.File

class Talks(val fileName: String) {
    lateinit var talks: MutableList<String>
    fun obtainTalks(): MutableList<String> {
        talks = File(fileName).readLines() as MutableList<String>
        return talks
    }
}