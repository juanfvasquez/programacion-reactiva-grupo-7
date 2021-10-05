interface Funcion1<A, B, C> {
    public C simple(A a, B b);
}

interface Funcion2<A, B> {
    public B curri(A a);
}

public class Currificacion {

    public static Funcion1<Integer, Integer, Integer> funcionSimple = new Funcion1<Integer, Integer, Integer>() {
        @Override
        public Integer simple(Integer x, Integer y) {
            return x + y;
        }
    };

    public static Funcion2<Integer, Funcion2<Integer, Integer>> funcionCurri = new Funcion2<Integer, Funcion2<Integer, Integer>>() {
        @Override
        public Funcion2<Integer, Integer> curri(Integer x) {
            return new Funcion2<Integer, Integer>() {
                @Override
                public Integer curri(Integer y) {
                    return x + y;
                }
            };
        }
    };

    public static void main(String[] args) {
        System.out.println(funcionSimple.simple(10, 2));
        System.out.println(funcionCurri.curri(10).curri(2));
        // curri(10)(2);
    }
}
