import java.io.File

class InputTalks {
    val fileName = "/Users/zeyang.li/Documents/Kotlin_test/Conference Track Management/src/Talks.txt"
    val talks: MutableList<String> = File(fileName).readLines() as MutableList<String>
}