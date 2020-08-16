val n = 26L
(1..n).sum()
n*(n+1)/2
fun check(a: Long, b: Long, n: Long):Boolean =
    n*(n+1)/2-a-b == a*b

val sumns = n*(n+1)/2

val res = (1L..n).filter { a -> (1L..n).filter { b -> a != b && sumns - (a+b) == a*b }.any() }.map { longArrayOf(it,(sumns-it)/(it+1)) }
res
res.joinToString(", ","[","]"){it.joinToString(", ","[","]")}

(1..n).filter{sumns/it < n}.map {
    val b = n*(it+1)>(sumns-it) && (sumns-it)%(it+1) == 0L
    if (b) longArrayOf(it,(sumns-it)/(it+1))
    else null
}.filterNotNull()

fun removNb(n: Long): Array<LongArray> {
    val sumns = n*(n+1)/2
    val res = (1..n).filter{sumns/it < n}.map {
        val b = (sumns-it)/(it+1)
        if (sumns == it*b + it+b) longArrayOf(it,b)
        else null
    }.filterNotNull()
    return res.toTypedArray()
}

removNb(1000003).joinToString(", ","[","]"){it.joinToString(", ","[","]")}

//check(15,21, 26)
//check(70,73, 102)
//check(70,73, 100)
//
//val a = 15L
//(1..n).filter { b -> a != b && check(a,b,n) }.map { a to it }
//(1L..n)
//    .map { a -> (1L..n)
//        .map{b -> a to b}
//        .filter { p -> p.first != p.second && check(p.first,p.second,n) } }
//    .filterNot { it.isEmpty() }
//    .flatMap { it.map{ longArrayOf(it.first,it.second) } }