import java.util.*;
import java.util.function.Function;

abstract class Vehicle {
    protected int hours;
    Vehicle(int hours) { this.hours = hours; }
    abstract double charge();
}

class Bike extends Vehicle {
    Bike(int h) { super(h); }
    double charge() { return hours * 10; }
}

class Car extends Vehicle {
    Car(int h) { super(h); }
    double charge() { return 30 + (hours - 1) * 20; }
}

class Truck extends Vehicle {
    Truck(int h) { super(h); }
    double charge() { return Math.max(100, hours * 50); }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Integer, Vehicle>> factory = new HashMap<>();
        factory.put("BIKE", Bike::new);
        factory.put("CAR", Car::new);
        factory.put("TRUCK", Truck::new);

        int n = sc.nextInt();
        List<Vehicle> vehicles = new ArrayList<>();
        List<String> types = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();
            types.add(type);
            vehicles.add(factory.get(type).apply(hours));
        }

        double total = 0;
        for (int i = 0; i < n; i++) {
            double c = vehicles.get(i).charge();
            total += c;
            System.out.println(String.format(Locale.US, "%s: %.2f", types.get(i), c));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}