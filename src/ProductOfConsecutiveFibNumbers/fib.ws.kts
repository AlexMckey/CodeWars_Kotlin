fun fibonacci() = sequence {
    var prevCur = 0L to 1L
    // this sequence is infinite
    while (true) {
        yield(prevCur.first)
        prevCur = prevCur.second to prevCur.first + prevCur.second
    }
}

var check = 714L

fibonacci().zipWithNext().dropWhile { it.first*it.second < check }.first()
var p = fibonacci().zipWithNext().dropWhile { it.first*it.second < check }.take(1).first()
var res = if (p.first*p.second == check) longArrayOf(p.first,p.second,1)
else longArrayOf(p.first,p.second,0)
res.joinToString()

check = 800L

p = fibonacci().zipWithNext().dropWhile { it.first*it.second < check }.take(1).first()
res = if (p.first*p.second == check) longArrayOf(p.first,p.second,1)
else longArrayOf(p.first,p.second,0)
res.joinToString()