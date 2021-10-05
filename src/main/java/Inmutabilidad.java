import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inmutabilidad {
    public static void main(String[] args) {
        final Inmutable i = new Inmutable(10, "Juan");
//        i = new Inmutable(20, "Pedro");
        i.getDatos().add("Dato 1");
        i.getDatos().add("Dato 2");
        i.getDatos().add("Dato 3");
        System.out.println(i.getDatos());
    }
}

class Inmutable {
    private final int edad;
    private final String nombre;
    private final List<String> datos;

    public Inmutable(int edad, String nombre) {
        this.edad = edad;
        this.nombre = nombre;
        this.datos = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getDatos() {
        return Collections.unmodifiableList(datos);
    }
}