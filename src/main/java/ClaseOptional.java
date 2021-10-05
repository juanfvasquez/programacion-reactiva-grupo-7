import java.util.Optional;
import java.util.function.Supplier;

public class ClaseOptional {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Referencia";
        Optional<String> op = Optional.empty();
        Optional<Integer> op2 = Optional.of(10);
//        op.empty();
        op.isPresent(); // boolean
        op.ifPresent(x -> System.out.println(x + " **** "));
//        op.ifPresent(System.out::println);
        String dato = op.orElse("default");
        System.out.println(dato);
//        op2.orElse(0);
        op.orElseGet(() -> "Por defecto");
        op.orElseGet(supplier);
        op2.orElseGet(() -> 0);

//        op.orElseThrow(() -> new Exception("No se encontró el elemento"));
    }
}
