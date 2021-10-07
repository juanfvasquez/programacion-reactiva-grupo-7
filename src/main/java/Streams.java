import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams {
    static List<String> cadenas = Arrays.asList("Cadena 1", "Cadena 2", "Cadena 32123", "");
    static List<Integer> numeros = Arrays.asList(1, 5, 7, 9, 12, 6754, 100);

    static List<Persona> personas = Arrays.asList(
            new Persona("Juan", 10),
            new Persona("Pedro", 30),
            new Persona("Ana", 30),
            new Persona("Sofía", 30)
    );

    public static void main(String[] args) {
//        usoFilter();
//        usoMap();
//        usoReduce();
        usoMatch();
    }

    private static void usoForEach() {
        cadenas.stream().forEach(s -> {
            String modificada = s.toUpperCase();
            System.out.println(modificada);
        });
    }

    private static void usoFilter() {
        Predicate<String> predicate = s -> s.contains("1");
//        cadenas.stream().filter(predicate); // Stream<String>
        cadenas.stream().filter(s -> s.contains("Cad")).forEach(System.out::println); // Stream<String>
        List<String> filtrados = cadenas.stream().filter(s -> s.contains("Cad")).collect(Collectors.toList());
        System.out.println(filtrados);

        numeros.stream().filter(x -> x > 0).forEach(System.out::println);
        personas.stream().filter(p -> p.edad > 20).forEach(System.out::println);
    }

    private static void usoMap() {
        cadenas.stream().map(s -> s.length()).forEach(System.out::println);
        List<Integer> nuevos = cadenas.stream().map(s -> s.length()).collect(Collectors.toList());
        System.out.println(nuevos);
        cadenas.stream().map(s -> s.length()).filter(x -> x == 8).forEach(System.out::println);

        cadenas.stream().map(s -> s + " - " + s).forEach(System.out::println);

        cadenas.stream().map(s -> new Persona(s, s.length())).forEach(System.out::println);
        cadenas.stream().map(s -> new Persona(s)).forEach(System.out::println);
        cadenas.stream().map(Persona::new).forEach(System.out::println);

        personas.stream().map(p -> p.edad).forEach(System.out::println);
        System.out.println(personas);
    }

    private static void usoReduce() {
//        int total = 0;
//        for (Integer i : numeros) {
//            total += i;
//        }
//        System.out.println(total);
        Integer total = numeros.stream().reduce((acum, num) -> acum + num).get();
        System.out.println(total);
        numeros.stream().reduce((acum, num) -> acum + num).ifPresent(System.out::println);

        cadenas.stream()
                .map(s -> s.length())
                .filter(x -> x <= 8)
                .reduce((acum, num) -> acum + num)
                .ifPresent(System.out::println);
    }

    private static void usoMatch() {
        // anyMatch, noneMatch, allMatch
        Predicate<String> predicate = s -> s.length() == 0;
        boolean hayCadenasVacias = cadenas.stream().anyMatch(predicate);
        System.out.println(hayCadenasVacias);
        boolean hayMayorEdad = personas.stream().anyMatch(p -> p.edad >= 38);
        System.out.println(hayMayorEdad);

        boolean hayNumerosNegativos = numeros.stream().noneMatch(x -> x < 0);
        System.out.println(hayNumerosNegativos);

        boolean mayoresEdad = personas.stream().allMatch(p -> p.edad >= 18);
        System.out.println(mayoresEdad);
    }
}
