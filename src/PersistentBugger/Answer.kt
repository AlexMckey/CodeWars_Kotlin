package PersistentBugger

tailrec fun persistence(num: Int, cnt: Int = 0) : Int =
        if (num < 10) cnt
        else persistence(num.toString()
            .map { it-'0' }
            .reduce { acc, i -> acc * i }, cnt+1)

//persistence(num.toString().map(Character::getNumericValue).reduce(Int::times), counter+1)