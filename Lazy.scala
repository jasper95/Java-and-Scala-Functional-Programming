def compute(): Int = {
    println("called...")
    4
}

val a = 4
val test = compute()

if(a > 5 && test > 5){
    println("inside if")
} else {
    println("inside else")
}
