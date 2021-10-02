import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class InterfacesFuncionales {

    static List<String> cadenas = Arrays.asList("Hola", "Cadena 1", "Juan", "999");

    public static void main(String[] args) {
//        usoPredicate();
//        usoConsumer();
//        usoSupplier();
//        usoFunction();
        usoBifunction();
    }

    private static void usoPredicate() {
//        Predicate<String> noVacia = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return false;
//            }
//        }
        Predicate<String> noVacia = s -> s.length() > 0;
        System.out.println(noVacia.test("Cad"));
        System.out.println(noVacia.test(""));
//        List<String> lista = new ArrayList<>();
//        lista.stream().filter(noVacia);
        Predicate<Persona> esMayorEdad = p -> p.edad >= 18;
        System.out.println(esMayorEdad.test(new Persona("Juan", 10)));

        Predicate<String> mas10Caracteres = s -> s.length() > 10;

        boolean metodoAnd = noVacia.and(mas10Caracteres).test("Cadena 10000");
        boolean metodoOr = noVacia.or(mas10Caracteres).test("Cadena 1");
        System.out.println(metodoAnd);

        Predicate<Integer> esMayorA100 = x -> x > 100;
        Predicate<Integer> esMenorACero = x -> x < 0;
        metodoOr = esMayorA100.or(esMenorACero).test(-50);
        System.out.println("Metodo OR: " + metodoOr);
    }

    private static void usoConsumer() {
        Consumer<String> imprimir = x -> System.out.println("Esta es la entrada: " + x);
//        imprimir.accept("Cadena");
//        cadenas.forEach(imprimir);
//        cadenas.forEach(s -> System.out.println("Entrada en Lambda: " + s));
        Consumer<String> imprimirMayusculas = s -> System.out.println("Texto en mayúsculas: " + cadenas.indexOf(s) + s.toUpperCase());

//        imprimir.andThen(imprimirMayusculas).andThen(imprimir).accept("texto de prueba");
//        imprimir.andThen(s -> System.out.println("Fin del consumer")).accept("Cadena Final");

//        pasarConsumer(cadenas, imprimir);
//        pasarConsumer(cadenas, imprimirMayusculas);
        pasarConsumer(cadenas, s -> {
            System.out.println("Salida totalmente diferente -> " + s);
            System.out.println("Salida totalmente diferente -> " + s);
        });
    }

    private static <T> void pasarConsumer(List<T> lista, Consumer<T> consumer) {
        for (T t: lista) {
            consumer.accept(t);
        }
    }

    private static void usoSupplier() {
        String idioma = "ingles";
        Supplier<String> saludoEspanol = () -> "Hola!";
        Supplier<String> saludoIngles = () -> "Hello!";

        pasoSupplier(idioma.equals("espanol") ? () -> "Hola!" : () -> "Hi!");
        Supplier<LocalDate> getFecha = () -> LocalDate.now();
        System.out.println(getFecha.get());
    }

    private static void pasoSupplier(Supplier<String> saludo) {
        System.out.println(saludo.get());
    }

    private static void usoFunction() {
        Function<String, Integer> longitud = s -> s.length();
//        System.out.println(longitud.apply("Cadena"));
        Function<String, Boolean> predicate = s -> s.length() > 10;
        Function<Integer, Integer> doble = x -> x * 2;
        Function<Integer, String> cadena = x -> {
            // Ejecutar cualquier proceso
            return "Esta es la respuesta: -> " + x;
        };
//        System.out.println(longitud.andThen(doble).apply("Cadena"));
        System.out.println(
                longitud
                        .andThen(doble)
                        .andThen(cadena)
                        .andThen(longitud)
                        .andThen(x -> "Este ya es el final: " + x)
                        .apply("Cadena")
        );
    }

    private static void usoBifunction() {
        BiFunction<Integer, String, String> bifunction = (x, s) -> x + ": " + s;
//        System.out.println(bifunction.apply(10, "Esta es la cadena"));
        BiFunction<Integer, Integer, Integer> mayor = (x, y) -> {
            System.out.println("----- dentro de Bifunction ---------");
            return x.compareTo(y) >= 0 ? x : y;
        };
//        System.out.println(mayor.apply(100, 10000));

        System.out.println(mayor.andThen(x -> {
            System.out.println("******* Dentro de Function (lambda) ********");
            return "Después de andThen: " + x;
        }).apply(10, 10000));
    }
}

class Persona {
    String nombre;
    int edad;

    Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}
