package week4.assignment_problems;

public class FoodOrder {

    String studentName, dishName;
    boolean delivered;

    FoodOrder(String studentName, String dishName) {

        if (studentName == null || studentName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid student name");

        if (dishName == null || dishName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid dish name");

        this.studentName = studentName;
        this.dishName = dishName;
    }

    void markDelivered() {

        if (!delivered) {
            delivered = true;
            System.out.println("Delivered: " + dishName);
        } else {
            System.out.println("Already delivered: " + dishName);
        }
    }

    static void processBatch(String[][] rawOrders) {

        int valid = 0, rejected = 0;

        for (String[] order : rawOrders) {

            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
    }

    public static void main(String[] args) {

        String[][] orders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        processBatch(orders);

        FoodOrder order =
                new FoodOrder("Ravi", "Paneer Butter Masala");

        order.markDelivered();
        order.markDelivered();
    }
}