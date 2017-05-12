import java.util.function.*;

class Lazy {

    @FunctionalInterface
    interface SomeInterface<T> {
        public T get();
    }


    static int compute(){
        System.out.println("called...");
        return 4;
    }

    public static void main(String[] args){
        int a = 4;

        //int test = compute();

        Supplier<Integer> test = () -> compute();
        // SomeInterface<Integer> test = new SomeInterface<Integer>(){
        //     @Override
        //     public Integer get(){
        //         return compute();
        //     }
        // };
        //SomeInterface<Integer> test = () -> compute();


        if(a > 5 && test.get() > 5){
            System.out.println("Inside if");
        } else {
            System.out.println("Inside else");
        }
    }
}
