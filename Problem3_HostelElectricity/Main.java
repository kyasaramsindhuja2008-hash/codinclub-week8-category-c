import java.util.*;
import java.util.function.Function;

abstract class Room {
    protected double units;
    Room(double units) { this.units = units; }
    abstract double bill();
}

class SingleRoom extends Room {
    SingleRoom(double units) { super(units); }
    double bill() { return units * 8; }
}

class SharedRoom extends Room {
    private int occupants;
    SharedRoom(double units, int occupants) {
        super(units);
        this.occupants = occupants;
    }
    double bill() { return units * 6 / occupants; }
}

class ACRoom extends Room {
    ACRoom(double units) { super(units); }
    double bill() { return units * 10 + 200; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<String[], Room>> factory = new HashMap<>();
        factory.put("SINGLE", p -> new SingleRoom(Double.parseDouble(p[1])));
        factory.put("SHARED", p -> new SharedRoom(Double.parseDouble(p[1]), Integer.parseInt(p[2])));
        factory.put("AC", p -> new ACRoom(Double.parseDouble(p[1])));

        int n = Integer.parseInt(sc.nextLine().trim());
        List<Room> rooms = new ArrayList<>();
        List<String> types = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split("\\s+");
            types.add(parts[0]);
            rooms.add(factory.get(parts[0]).apply(parts));
        }

        double total = 0;
        for (int i = 0; i < n; i++) {
            double b = rooms.get(i).bill();
            total += b;
            System.out.println(String.format(Locale.US, "%s: %.2f", types.get(i), b));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}