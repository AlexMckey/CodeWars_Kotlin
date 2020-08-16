tailrec fun persistCnt(num: Int, cnt: Int = 0): Int =
    if (num < 10) cnt
    else persistCnt(num.toString()
        .map { it-'0' }
        .reduce { acc, i -> acc * i }, cnt+1)

12.toString().map { it-'0' }

persistCnt(4)
persistCnt(12)
persistCnt(999)

