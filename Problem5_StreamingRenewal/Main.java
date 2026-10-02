import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;

abstract class Plan {
    protected String name;
    protected LocalDate startDate;
    Plan(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }
    String getName() { return name; }
    abstract LocalDate renewalDate();
}

class BasicPlan extends Plan {
    BasicPlan(String n, LocalDate d) { super(n, d); }
    LocalDate renewalDate() { return startDate.plusDays(30); }
}

class StandardPlan extends Plan {
    StandardPlan(String n, LocalDate d) { super(n, d); }
    LocalDate renewalDate() { return startDate.plusDays(90); }
}

class PremiumPlan extends Plan {
    PremiumPlan(String n, LocalDate d) { super(n, d); }
    LocalDate renewalDate() { return startDate.plusDays(365); }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, BiFunction<String, LocalDate, Plan>> factory = new HashMap<>();
        factory.put("BASIC", BasicPlan::new);
        factory.put("STANDARD", StandardPlan::new);
        factory.put("PREMIUM", PremiumPlan::new);

        int n = sc.nextInt();
        List<Plan> subscribers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            LocalDate start = LocalDate.parse(sc.next());
            subscribers.add(factory.get(type).apply(name, start));
        }

        for (Plan p : subscribers) {
            System.out.println(p.getName() + ": " + p.renewalDate());
        }
    }
}