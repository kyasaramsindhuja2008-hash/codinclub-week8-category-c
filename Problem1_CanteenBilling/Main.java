import java.util.*;
import java.util.function.Function;

abstract class Customer {
    protected double amount;
    Customer(double amount) { this.amount = amount; }
    abstract double finalAmount();
}

class Student extends Customer {
    Student(double a) { super(a); }
    double finalAmount() { return amount * 0.90; }
}

class Staff extends Customer {
    Staff(double a) { super(a); }
    double finalAmount() { return amount * 0.95; }
}

class Guest extends Customer {
    Guest(double a) { super(a); }
    double finalAmount() { return amount + 10; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Double, Customer>> factory = new HashMap<>();
        factory.put("STUDENT", Student::new);
        factory.put("STAFF", Staff::new);
        factory.put("GUEST", Guest::new);

        int n = sc.nextInt();
        List<Customer> bills = new ArrayList<>();
        List<String> types = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amt = sc.nextDouble();
            types.add(type);
            bills.add(factory.get(type).apply(amt));
        }

        double total = 0;
        for (int i = 0; i < n; i++) {
            double f = bills.get(i).finalAmount();
            total += f;
            System.out.println(String.format(Locale.US, "%s: %.2f", types.get(i), f));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}