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
    var tracks = mutableListOf<Track>()
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