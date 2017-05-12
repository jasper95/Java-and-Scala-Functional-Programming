import java.util.function.*;
import java.util.*;
import java.util.stream.Collectors;

class Streams {
    public static void main(String[] args){
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(fold(Integer::sum,
                                0,
                                map(e -> e * 2,
                                    filter(e -> e % 2 == 1,
                                        filter(e -> e > 5, values)
                                    )
                                )
                            ));
        System.out.println(values
                            .stream()
                            .filter(e -> e > 5)
                            .filter(e -> e % 2 == 1)
                            .map(e -> e * 2)
                            .reduce(0, (a,b) -> a + b));
        System.out.println(values
                            .stream()
                            .filter(Streams :: isGT5)
                            .filter(Streams :: isOdd)
                            .map(Streams :: doubleIt)
                            .findFirst());
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

    public static List<Integer> map(IntUnaryOperator f, List<Integer> list){
        ArrayList<Integer> result = new ArrayList<>();
        if(!list.isEmpty()){
            ArrayList<Integer> list2 = new ArrayList<>(list);
            Integer first = list2.remove(0);
            result.add(f.applyAsInt(first));
            result.addAll(map(f, list2)); //returns boolean
            return result;
        }
        else return result;
    }

    static List<Integer> filter(IntPredicate f, List<Integer> list){
        ArrayList<Integer> result = new ArrayList<>();
        if(!list.isEmpty()){
            ArrayList<Integer> list2 = new ArrayList<>(list);
            Integer first = list2.remove(0);
            if(f.test(first))
                result.add(first);
            result.addAll(filter(f, list2)); //returns boolean
            return result;
        }
        else return result;
    }

    static Integer fold(IntBinaryOperator f, int accum, List<Integer> list){
        ArrayList<Integer> result = new ArrayList<>();
        if(!list.isEmpty()){
            ArrayList<Integer> list2 = new ArrayList<>(list);
            Integer first = list2.remove(0);
            return fold(f, f.applyAsInt(accum, first), list2);
        }
        else return accum;
    }

}
