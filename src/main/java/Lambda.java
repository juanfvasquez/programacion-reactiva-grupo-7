import java.util.List;

@FunctionalInterface
interface Operacion {
    public void operar();
}
interface Doble {
    public double duplicar(int x);
}
interface Suma {
    public double sumar(int x, double y);
}

interface Lista {
    public List<String> listar(List<String> lista);
}

public class Lambda {
    public static void main(String[] args) {
        // () -> { }
//        Operacion op = new Operacion() {
//            public void operar() {
//                System.out.println("Clase anónima");
//            }
//        };
//        op.operar();

        Operacion op = () -> {
            System.out.println("Expresión Lambda");
        };
        op = () -> System.out.println("Expresión Lambda");
        lambdaParametro(() -> System.out.println("Segunda expresión lambda"));
        Doble doble = (int x) -> x * 2;
        doble = (x) -> x * 2;
        doble = x -> x * 2;

        Suma suma = (x, y) -> x + y;
        suma = Lambda::referenciaSuma;

        suma.sumar(1, 2);

        Lista lista = l -> l;
    }

    public static void lambdaParametro(Operacion op) {
        op.operar();
    }

    public static double referenciaSuma(int x, double y) {
        return x + y;
    }
}
