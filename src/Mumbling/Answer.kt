package Mumbling

fun accum(s:String):String =
    s.mapIndexed { i, c -> c.toLowerCase().toString().repeat(i+1).capitalize()}
        .joinToString("-")