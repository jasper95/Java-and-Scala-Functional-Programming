val add2 = (y : Int) => y + 2
val add3 = (y : Int) => y + 3


val compose = (f : Int => Int, g: Int => Int) => (x : Int) => g(f(x))
val add5Composed = compose(add2, add3)

println("Simple Function --- add2(8) = %d".format(add2(8)))
println("Function composition using compose --- add5Composed(5) = %d".format(add5Composed(5)))


val adder = (y : Int) => (x : Int) => x + y
val add2Curry = adder(2)

println("Currying Function --- add2Curry(8) = %d".format(add2Curry(8)))
println("Function Application --- adder(2)(8) = %d".format(adder(2)(8)))
