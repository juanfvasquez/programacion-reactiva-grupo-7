import java.util.function.Predicate;

public class InterfacesFuncionales {
    public static void main(String[] args) {
        usoPredicate();
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
}

class Persona {
    String nombre;
    int edad;

    Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}
