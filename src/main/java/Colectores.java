import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Colectores {
    static List<Persona> personas = Arrays.asList(
            new Persona("Juan", 10),
            new Persona("Pedro", 30),
            new Persona("Ana", 30),
            new Persona("Sofía", 30)
    );

    public static void main(String[] args) {
//        collectorsBasicos();
//        collectorsColeccion();
        collectorsAgrupamiento();
    }

    private static void collectorsBasicos() {
        List<Integer> numeros = Arrays.asList(1, 5, 7, 9, 12, 6754, 100);
        System.out.println(numeros.stream().collect(Collectors.summingInt(Integer::intValue)));
        System.out.println(numeros.stream().collect(Collectors.averagingInt(Integer::intValue)));

        List<String> cadenas = Arrays.asList("Cad 1", "Cad 2");
        System.out.println(cadenas.stream().collect(Collectors.joining()));

        IntSummaryStatistics resumen = numeros.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("----------- Resultados -------------");
        System.out.println("Mayor: " + resumen.getMax());
        System.out.println("Menor: " + resumen.getMin());
        System.out.println("Promedio: " + resumen.getAverage());
        System.out.println("Suma: " + resumen.getSum());
        System.out.println("Conteo: " + resumen.getCount());

        resumen = personas.stream().collect(Collectors.summarizingInt(p -> p.edad));
        System.out.println("----------- Resultado Personas -------------");
        System.out.println("Mayor: " + resumen.getMax());
        System.out.println("Menor: " + resumen.getMin());
        System.out.println("Promedio: " + resumen.getAverage());
        System.out.println("Suma: " + resumen.getSum());
        System.out.println("Conteo: " + resumen.getCount());

        resumen = cadenas.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println("----------- Resultado Strings -------------");
        System.out.println("Mayor: " + resumen.getMax());
        System.out.println("Menor: " + resumen.getMin());
        System.out.println("Promedio: " + resumen.getAverage());
        System.out.println("Suma: " + resumen.getSum());
        System.out.println("Conteo: " + resumen.getCount());

//        resumen = cadenas.stream().collect(Collectors.summarizingInt(s -> Integer.parseInt(s)));
    }

    private static void collectorsColeccion() {
        List<Integer> numeros = Arrays.asList(1, 5, 7, 9, 12, 6754, 100, 1, 2, 3, 12, 25);
//        System.out.println("Lista original");
//        System.out.println(numeros);
//
//        System.out.println(numeros.stream().filter(x -> x < 1000).collect(Collectors.toList()));
//        System.out.println(numeros.stream().filter(x -> x < 1000).collect(Collectors.toSet()));
//        System.out.println(numeros.stream().distinct().collect(Collectors.toMap(x -> x, x -> "Este es el valor: " + x)));

        Empleado juan = new Empleado("Juan", 2000, "Sistemas");
        Empleado lucia = new Empleado("Lucía", 4000, "Ventas");
        Empleado ana = new Empleado("Ana", 3000, "Compras");
        Empleado pedro = new Empleado("Pedro", 2500, "Compras");

        List<Empleado> empleados = Arrays.asList(juan, ana, lucia, pedro, ana, pedro, juan);
        System.out.println("Lista original");
        System.out.println(empleados);
        System.out.println(empleados.stream().collect(Collectors.toList()));
        System.out.println(empleados.stream().collect(Collectors.toSet()));
        System.out.println(empleados.stream().distinct().collect(Collectors.toMap(e -> e.nombre, e -> e.salario)));
        System.out.println(empleados.stream().distinct().collect(Collectors.toMap(Empleado::getNombre, Empleado::getSalario)));
        Function<Empleado, String> keyMapper = e -> e.getNombre();
        Function<Empleado, Double> valueMapper = e -> e.getSalario();
        System.out.println(empleados.stream().distinct().collect(Collectors.toMap(keyMapper, valueMapper)));
    }

    private static void collectorsAgrupamiento() {
        Empleado juan = new Empleado("Juan", 2000, "Sistemas");
        Empleado lucia = new Empleado("Lucía", 4000, "Ventas");
        Empleado ana = new Empleado("Ana", 3000, "Compras");
        Empleado pedro = new Empleado("Pedro", 2500, "Compras");

        List<Empleado> empleados = Arrays.asList(juan, ana, lucia, pedro, juan);

//        Map<Boolean, List<Empleado>> resultado = empleados.stream().collect(Collectors.partitioningBy(e -> e.getSalario() > 3000));
        System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.getSalario() > 3000)));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.getSalario() > 3000)));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.getSalario())));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.getArea())));
        System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> {
            double total = 0;
            double impuesto = e.getSalario() * .23;
            if (e.getArea().equalsIgnoreCase("sistemas")) {
                total += 3000;
            }
            return total + impuesto + e.getSalario();
        })));

        System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.getSalario() > 3000, Collectors.groupingBy(Empleado::getArea))));
        System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.getSalario() > 3000, Collectors.groupingBy(Empleado::getSalario))));

        System.out.println(
                empleados.stream().collect(
                        Collectors.partitioningBy(e -> e.getSalario() > 3000,
                                Collectors.groupingBy(Empleado::getArea,
                                        Collectors.counting())))
        );
    }
}

class Empleado {
    String nombre;
    double salario;
    String area;

    public Empleado(String nombre, double salario, String area) {
        this.nombre = nombre;
        this.salario = salario;
        this.area = area;
    }

    @Override
    public String toString() {
        return nombre + "(" + area + ", " + salario + ")";
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public String getArea() {
        return area;
    }
}