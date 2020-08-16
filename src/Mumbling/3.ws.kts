val c = 'B'
val i = 5
val ca = List(i+1){c.toLowerCase()}.joinToString("").capitalize()
ca

val str = "AbC"
str.mapIndexed { i, c -> c.toLowerCase().toString().repeat(i+1).capitalize()}
    .joinToString("-")