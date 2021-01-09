fun parseTalk(talk: String): TalkAndTimes {
    val splitedTalk = talk.split(" ")
    val lastIndexValue = splitedTalk[splitedTalk.size - 1]
    if (lastIndexValue == "lightning") {
        return TalkAndTimes(talk, 5)
    } else {
        return TalkAndTimes(talk, lastIndexValue.substringBefore("min").toInt())
    }
}

fun assignTalks(talks: MutableList<TalkAndTimes>): MutableList<Track> {
    val sortedTalks = talks.sortedBy { talk -> talk.times }
    var track = Track()
    var morningSession = MorningSession()
    var afternoonSession = AfternoonSession()
    var morningRemainingTime = morningSession.RemainingTime
    var afternoonRemainingTime = afternoonSession.RemainingTime
    val tracks = mutableListOf<Track>()
    for (talk in sortedTalks) {
        if (morningRemainingTime - talk.times >= 0) {
            morningRemainingTime -= talk.times
            morningSession.selectedtalks.add(talk)
        } else if (afternoonRemainingTime - talk.times >= 0) {
            afternoonRemainingTime -= talk.times
            afternoonSession.selectedtalks.add(talk)
        } else {
            track.morningSession = morningSession
            track.afternoonSession = afternoonSession
            tracks.add(track)
            track = Track()
            morningSession = MorningSession()
            afternoonSession = AfternoonSession()
            morningRemainingTime = morningSession.RemainingTime
            afternoonRemainingTime = afternoonSession.RemainingTime
        }
    }
    track.morningSession = morningSession
    track.afternoonSession = afternoonSession
    tracks.add(track)
    return tracks
}

fun parseTime(tracks: MutableList<Track>) {
    for (index in tracks.indices) {
        println("Track ${index + 1}:")
        var hour: Int
        var minute: Int
        var morningTimeConsumed = 0
        val morningStartTime = 9
        for (talkAndTimes in tracks[index].morningSession.selectedtalks) {
            hour = morningTimeConsumed / 60
            morningTimeConsumed += talkAndTimes.times
            minute = (morningTimeConsumed - talkAndTimes.times) % 60
            val time = String.format("%02d:%02d", morningStartTime + hour, minute)
            println(time + "AM ${talkAndTimes.talk}")
        }
        println("12:00PM Lunch")
        var afternoonTimeConsumed = 0
        val afternoonStartTime = 1
        for (talkAndTimes in tracks[index].afternoonSession.selectedtalks) {
            hour = afternoonTimeConsumed / 60
            afternoonTimeConsumed += talkAndTimes.times
            minute = (afternoonTimeConsumed - talkAndTimes.times) % 60
            val time = String.format("%02d:%02d", afternoonStartTime + hour, minute)
            println(time + "PM ${talkAndTimes.talk}")
        }
        if (afternoonTimeConsumed <= 180) {
            println("04:00PM Networking Event")
        } else {
            println("05:00PM Networking Event")
        }
        println("\n")
    }
}