val testList = List(1,2,3,4,5,6,7,8,9,10)
println(fold((x: Int, y: Int) => x + y,
            0,
            map((x : Int) => x * 2,
                filter((x : Int) => x > 5,
                    filter((x : Int) => x % 2 == 1, testList)
                )
            )
        ))

println(testList
                .filter(x => x > 5)
                .filter(x => x % 2 == 1)
                .map(x => x * 2)
                .reduce((x, y) => x + y))

println(testList
                .filter(isGT5)
                .filter(isOdd)
                .map(doubleIt)
                .headOption)
val x = 8

Stream
    .from(1)
    .map(x => power(x, x))
    .take(x)
    .foreach(println)

def power(x: BigInt, y : BigInt): BigInt = {
    if (y == 0)
        1
    else if (y.mod(BigInt(2)) == 0) {
        val m = power(x, y./(BigInt(2)))
        m.*(m)
    } else x.*(power(x, y.-(BigInt(1))))
}


def isOdd(x : Int) : Boolean = {
    println("isOdd called... %d".format(x))
    x % 2 == 1
}

def isGT5(x : Int) : Boolean = {
    println("isGT5 called... %d".format(x))
    x > 5
}

def doubleIt(x : Int) : Int = {
    println("doubleIt called... %d".format(x))
    x * 2
}

def map[A, B](f: (A) => B, list: List[A]) : List[B] = list match {
    case (x :: xs) => {
        f(x) +: map(f, xs)
    }
    case _ => Nil
}

def filter[A](f: (A) => Boolean, list: List[A]) : List[A] = list match {
    case (x :: xs) => {
        if(f(x))
            x +: filter(f, xs)
        else
            filter(f, xs)

    }
    case _ => Nil
}

def fold[A](f: (A, A) => A, acc: A, list: List[A]) : A = list match {
    case (x :: xs) => {
        fold(f, f(acc, x), xs)
    }
    case _ => acc
}
