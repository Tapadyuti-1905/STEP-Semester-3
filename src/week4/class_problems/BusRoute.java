package week4.class_problems;

public class BusRoute {

    String routeCode, routeName;
    int priority;

    BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    int compareTo(BusRoute other) {

        // Higher priority first
        if (priority != other.priority)
            return Integer.compare(other.priority, priority);

        // Route code, ignoring case
        int code = routeCode.compareToIgnoreCase(other.routeCode);

        if (code != 0)
            return code;

        // Shorter name first
        return Integer.compare(routeName.length(), other.routeName.length());
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = routes.clone();

        // Manual stable bubble sort
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1 - i; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {
                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        for (BusRoute r : ranked)
            System.out.println(r.routeCode);
    }
}