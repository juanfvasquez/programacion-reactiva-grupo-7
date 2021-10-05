import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrdenSuperior {
    final static Function<String, String> mayusculas = s -> s.toUpperCase();

    private static void imprimirFunction(Function<String, String> funcion, String s) {
        System.out.println(funcion.apply(s));
    }

    public Function<String, String> devolverFunction(String cad) {
        return s -> s + " -> " + cad;
    }

    public void filtrar(List<String> lista, Predicate<String> criterio, Consumer<String> consumer) {
//        List<String> filtrados = new ArrayList<>();
//        for (String s: lista) {
//            if (criterio.test(s)) {
//                filtrados.add(s);
//            }
//        }
//        System.out.println(filtrados);

//        filtrados = lista.stream().filter(criterio).collect(Collectors.toList());
        lista.stream().filter(criterio).forEach(consumer);
    }

    public static void main(String[] args) {
        imprimirFunction(mayusculas, "Cualquier cosa");
    }
}
