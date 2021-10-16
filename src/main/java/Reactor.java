import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class Reactor {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("String");
//        mono.subscribe(s -> System.out.println(s));

//        Mono.just(new Persona("Juan", 0)).subscribe(System.out::println);

        List<String> cadenas = Arrays.asList("Cad 1", "Cad 2", "Cad 3...");
//        Mono.just(cadenas)
//                .subscribe(System.out::println);

//        Flux.fromIterable(cadenas).subscribe(System.out::println);
        Flux<Integer> flux = Flux.create(sink -> {
            for (int i = 0; i < 100; i++) {
                sink.next(i);
            }
            sink.complete();
        });

        Flux<String> flux2 = flux
                .filter(x -> x % 2 == 0)
                .map(x -> "La cadena es: " + x);

        flux2.subscribe(System.out::println);
    }
}
