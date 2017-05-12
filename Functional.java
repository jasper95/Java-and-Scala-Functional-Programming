import java.util.function.*;

class Functional{

    public static void main(String[] args){
        Function<Integer, Integer> add2 = y -> 2 + y,
                                    add3 = y -> 3 + y;


        BinaryOperator<Function<Integer, Integer>> compose = (f ,g) -> x -> g.apply(f.apply(x));  // h(x) = f(g(x))
        // BinaryOperator<Function<Integer, Integer>> compose = new BinaryOperator<Function<Integer, Integer>>(){
        //     @Override
        //     public Function<Integer, Integer> apply(Function<Integer, Integer> f, Function<Integer, Integer> g){
        //         return new Function<Integer, Integer>(){
        //             @Override
        //             public Integer apply(Integer x){
        //                 return g.apply(f.apply(x));
        //             }
        //         };
        //     }
        // };

        Function<Integer,Integer> add5Composed = compose.apply(add2, add3);

        System.out.println(String.format("Simple Function --- add2.apply(8) = %d", add2.apply(8))); //10
        System.out.println(String.format("Function<T,R> Compositiopn using BinaryOperator --- add5Composed.apply(5)) = %d", add5Composed.apply(5))); //10
        System.out.println(String.format("Function<T,R> Composition using .compose() --- add3.compose(add2).apply(5)) = %d", add3.compose(add2).apply(5)) ); //10


        Function<Integer, Function<Integer, Integer>> makeAdder = x -> y -> x + y;
        //Function<Integer, Function<Integer, Integer>> makeAdder = Functional::adder;

        Function<Integer, Integer> add2Curry = makeAdder.apply(2); //makeAdder 2



        System.out.println(String.format("Currying Function --- add2Curry.apply(8) = %d", add2Curry.apply(8))); // 10
        System.out.println(String.format("Function Application --- makeAdder.apply(2).apply(8) = %d", makeAdder.apply(2).apply(8))); //makeAdder 2 8 = 10

    }

    public static Function<Integer, Integer> adder(Integer x) {
       return y -> x + y;
    }
}
