fun parseTalk(talk: String): TalkAndTimes {
    //Writing Fast Tests Against Enterprise Rails 60min
    val splitedTalk = talk.split(" ")
    val times = splitedTalk[splitedTalk.size - 1].substringBefore("min")
    val talkAndTimes = TalkAndTimes(talk, times.toInt())
    return talkAndTimes
}