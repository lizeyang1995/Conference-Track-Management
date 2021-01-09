fun parseTalk(talk: String): TalkAndTimes {
    val splitedTalk = talk.split(" ")
    val times = splitedTalk[splitedTalk.size - 1].substringBefore("min")
    val talkAndTimes = TalkAndTimes(talk, times.toInt())
    return talkAndTimes
}