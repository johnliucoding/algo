package semantic.stream;

import java.util.List;

import static java.util.stream.Collectors.*;

public class TeeExample {

    enum Color {
        RED, BLUE, WHITE, YELLOW
    }

    enum Engine {
        ELECTRIC, HYBRID, GAS
    }

    enum Drive {
        WD2, WD4
    }

    sealed interface Vehicle {
    }

    record Car(Color color, Engine engine, Drive drive, int passengers) implements Vehicle {
    }

    record Truck(Engine engine, Drive drive, int weight) implements Vehicle {
    }

    static void main() {
        List<Vehicle> vehicles = List.of(
                new Car(Color.BLUE, Engine.ELECTRIC, Drive.WD2, 4),
                new Car(Color.WHITE, Engine.HYBRID, Drive.WD4, 5),
                new Truck(Engine.GAS, Drive.WD4, 12_000),
                new Truck(Engine.GAS, Drive.WD2, 8_000),
                new Truck(Engine.ELECTRIC, Drive.WD2, 9_000)
        );

        List<Vehicle> electricVehicles = vehicles.stream()
                .collect(
                        teeing(
                                filtering(
                                        vehicle -> vehicle instanceof Car car && car.engine() == Engine.ELECTRIC,
                                        toList()),

                                filtering(
                                        vehicle -> vehicle instanceof Truck truck && truck.engine() == Engine.ELECTRIC,
                                        toList()),

                                (cars, trucks) -> {
                                    cars.addAll(trucks);
                                    return cars;
                                }));
        IO.println("Electric vehicles = " + electricVehicles);

        List<Vehicle> list = vehicles.stream().filter(TeeExample::isElectric).toList();
        IO.println("Electric vehicles2 = " + list);

    }


    static boolean isElectric(Vehicle vehicle) {
        return switch (vehicle) {
            case Car car when car.engine() == Engine.ELECTRIC -> true;
            case Truck truck when truck.engine() == Engine.ELECTRIC -> true;
            case Car _, Truck _ -> false;
        };
    }


}
