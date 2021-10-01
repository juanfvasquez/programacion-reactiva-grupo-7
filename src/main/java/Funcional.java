import java.util.Arrays;
import java.util.List;

public class Funcional {

    public static void main(String[] args) {
//        ejemploTradicional();
        ejemploFuncional();
    }

    public static void ejemploTradicional() {
        List<Integer> numeros = Arrays.asList(1, 5, 8, 9, 12, 7, 9, 12);
        int contador = 0;
        for (Integer i: numeros) {
            if (i > 10) {
                contador++;
            }
        }
        System.out.println(contador);
    }

    public static void ejemploFuncional() {
        List<Integer> numeros = Arrays.asList(1, 5, 8, 9, 12, 7, 9, 12);
        long contador = numeros
                .stream()
                .filter(x -> x > 10)
                .count();
        System.out.println(contador);
    }
}
