import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.jvm.hotspot.oops.Array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reactor {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("String");
//        mono.subscribe(s -> System.out.println(s));

//        Mono.just(new Persona("Juan", 0)).subscribe(System.out::println);

        List<String> cadenas = Arrays.asList("Cad 1", "Cad 2", "Cad 3...", "Cad 1");
//        Mono.just(cadenas)
//                .subscribe(System.out::println);

//        Flux.fromIterable(cadenas).subscribe(System.out::println);
        Flux<Integer> flux = Flux.create(sink -> {
            for (int i = 0; i < 5; i++) {
                sink.next(i);
            }
            sink.complete();
        });

        Flux<Integer> fluxCopia = Flux.create(sink -> {
            for (int i = 100; i < 120; i++) {
                sink.next(i);
            }
            sink.complete();
        });

        Flux<String> flux2 = flux
                .filter(x -> x % 2 == 0)
                .map(x -> "La cadena es: " + x);

//        flux2.subscribe(System.out::println);

//        Flux.fromIterable(cadenas)
//                .map(s -> new Persona(s, s.length()))
//                .filter(p -> p.edad <= 5)
//                .groupBy(p -> p.nombre)
//                .flatMap(f -> f.collectList())// [1, [2, 3], [4, 5]] -> [1, 2, 3, 4, 5]
//                .subscribe(System.out::println);

//        Flux.fromIterable(cadenas)
//                .collect(Collectors.groupingBy(s -> s.length()))
//                .subscribe(System.out::println);

        List<String> productosFactura = Arrays.asList("Producto 1", "Producto 2", "Producto 3");
        List<Factura> facturas = Arrays.asList(
                new Factura(productosFactura),
                new Factura(productosFactura),
                new Factura(productosFactura),
                new Factura(productosFactura)
        );

        Flux<Factura> fluxFacturas = Flux.fromIterable(facturas);
        Flux<String> fluxProductos = fluxFacturas
                .flatMap(f -> Flux.fromIterable(f.productos));

//        System.out.println(fluxProductos.collect(Collectors.toList()).block());
        // [ f([]), f([]) ] -> [ ... ]

        Flux.merge(flux, fluxCopia)
                .map(x -> "Numero : " + x)
                .subscribe(System.out::println);

        Flux.zip(flux, fluxCopia, (x1, x2) -> String.format("Flux 1: %d - Flux 2: %d", x1, x2))
                .subscribe(System.out::println);
    }
}

class Factura {
    List<String> productos;

    public Factura(List<String> productos) {
        this.productos = productos;
    }
}