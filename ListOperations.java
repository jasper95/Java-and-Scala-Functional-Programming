import java.util.function.*;
import java.util.*;
import java.util.stream.Collectors;

class ListOperations {
    public static void main(String[] args){
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(fold((a, b) -> a + b,
                                0,
                                map(e -> e * 2,
                                    filter(e -> e % 2 == 1,
                                        filter(e -> e > 5, values)
                                    )
                                )
                            ));
        // System.out.println(values
        //                     .stream()
        //                     .filter(e -> e > 5)
        //                     .filter(e -> e % 2 == 1)
        //                     .map(e -> e * 2)
        //                     .reduce(0, (a,b) -> a + b));
        // System.out.println(values
        //                     .stream()
        //                     .filter(ListOperations :: isGT5)
        //                     .filter(ListOperations :: isOdd)
        //                     .map(ListOperations :: doubleIt)
        //                     .findFirst());
    }

    static int doubleIt(int x){
        System.out.println(String.format("doubleIt called... %d", x));
        return x * 2;
    }

    static boolean isOdd(int a){
        System.out.println(String.format("isOdd called... %d", a));
        return a % 2 == 1;
    }

    static boolean isGT5(int a){
        System.out.println(String.format("isGT5 called... %d", a));
        return a > 5;
    }

    static <T, R> List<R> map(Function<T, R> f, List<T> list){
        ArrayList<R> result = new ArrayList<>();
        if(!list.isEmpty()){
            ArrayList<T> list2 = new ArrayList<>(list);
            T first = list2.remove(0);
            result.add(f.apply(first));
            result.addAll(map(f, list2)); //returns boolean
            return result;
        }
        else return result;
    }

    static <T> List<T> filter(Function<T, Boolean> f, List<T> list){
        ArrayList<T> result = new ArrayList<>();
        if(!list.isEmpty()){
            ArrayList<T> list2 = new ArrayList<>(list);
            T first = list2.remove(0);
            if(f.apply(first))
                result.add(first);
            result.addAll(filter(f, list2)); //returns boolean
            return result;
        }
        else return result;
    }

    static <T> T fold(BinaryOperator<T> f, T accum, List<T> list){
        ArrayList<T> result = new ArrayList<>();
        if(!list.isEmpty()){
            ArrayList<T> list2 = new ArrayList<>(list);
            T first = list2.remove(0);
            return fold(f, f.apply(accum, first), list2);
        }
        else return accum;
    }

}
