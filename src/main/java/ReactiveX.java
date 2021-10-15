import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class ReactiveX {
    public static void main(String[] args) {
        Integer[] numeros = {1, 4, 6, 8, 9, 353, 654, 123, 7865, 5000};
        List<String> cadenas = Arrays.asList("Cadena 1", "Cadena 2", "...");
        Observable.just(100, 100);
        Observable.just(new Coord());
        Observable obs = Observable.from(numeros);

//        obs.subscribe(o -> System.out.println(o));
//        Observable.from(cadenas).subscribe(System.out::println);

        Observable.from(numeros)
//                .filter(x -> x < 100)
//                .first()
//                .last()
//                .take(3)
//                .skip(3)
//                .skipLast(3)
                .map(x -> {
                    return Math.pow(x, 2);
                })
                .map(x -> {
                    Coord coord = new Coord();
                    coord.x = x;
                    coord.y = Math.pow(x, 2);
                    return coord;
                })
//                .reduce((c1, c2) -> new Coord(c1.x + c2.x, c1.y + c2.y))
//                .count()
//                .all(c -> c.x > 1)
//                .toMap(c -> c.x)
//                .toMap(c -> c.toString(), c -> c.x + c.y)
                .toMap(Coord::toString, Coord::suma)
                .subscribe(System.out::println);

    }
}

class Coord {
    double x, y;

    Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Coord() {
        x = 0;
        y = 0;
    }

    @Override
    public String toString() {
        return "Coord (" + x + " - " + y + ")";
    }

    public double suma() {
        return x + y;
    }
}