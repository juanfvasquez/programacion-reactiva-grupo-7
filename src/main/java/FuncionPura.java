import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncionPura {
    static int y = 10;
    static Map<Integer, Double> impuestos;

    public static void main(String[] args) {
//        System.out.println(sumaPura(20, 10)); // 30
//        y = 20;
//        System.out.println(sumaPura(20, 10)); // 30

        impuestos = new HashMap<>();
        List<Integer> facturas = Arrays.asList(
                50000, 20000, 60000, 40000, 10000,
                42000, 54500, 12000, 10000, 20000,
                50000, 20000, 60000, 40000, 10000,
                42000, 54500, 12000, 10000, 20000
        );
        for (Integer i : facturas) {
            Double impuesto = impuestos.get(i);
            if (impuesto == null) {
                impuesto = calculoImpuesto(i);
                impuestos.put(i, impuesto);
            } else {
                System.out.println("Tomando resultado del mapa para " + i);
            }
        }
    }

    private static int sumaImpura(int x) {
        return x + y;
    }

    private static int sumaPura(int x, int y) {
        return x + y;
    }

    // Memoizacion - Memoize
    private static double calculoImpuesto(int valor) {
        System.out.println("Cálculo para " + valor);
        double impuesto = valor * 0.6;
        return impuesto;
    }
}
