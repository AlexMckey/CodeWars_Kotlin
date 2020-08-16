package IsMyFriendCheating

object RemovedNumbers {
    fun removNb(n: Long): Array<LongArray> {
        val sumns = n*(n+1)/2
        val res = (1..n).filter{sumns/it < n}.map {
            val b = (sumns-it)/(it+1)
                if (sumns == it*b + it+b) longArrayOf(it,b)
            else null
        }.filterNotNull()
        return res.toTypedArray()
    }
}