import java.util.*

fun main() {
    val talks = Talks("/Users/zeyang.li/Documents/Kotlin_test/Conference-Track-Management/src/Talks.txt").obtainTalks()
    val parsedTalks = mutableListOf<TalkAndTimes>()
    for (talk in talks) {
        val parsedTalk = parseTalk(talk)
        parsedTalks.add(parsedTalk)
    }
    val tracks = assignTalks(parsedTalks)
    parseTime(tracks)
}