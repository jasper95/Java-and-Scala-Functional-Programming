val testList = List(1,2,3,4,5,6,7,8,9,10)

println(fold((x: Int, y: Int) => x + y,
            0,
            map((x : Int) => x * 2,
                filter((x : Int) => x > 5,
                    filter((x : Int) => x % 2 == 1, testList)
                )
            )
        ))

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
