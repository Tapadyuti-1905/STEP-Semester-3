package week6.class_problems;

class FestEventTicket {
    private static int counter = 1000;
    private final String ticketId;
    protected double balance;

    FestEventTicket(double price) {
        ticketId = "TCK-" + (++counter);
        balance = price;
    }

    void pay(double amount) {
        balance -= amount;
    }

    void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'F'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    static int getTicketsIssued() {
        return counter - 1000;
    }

    double getBalanceDue() {
        return balance;
    }
}

class GroupTicket extends FestEventTicket {
    int groupSize;

    GroupTicket(double price, int groupSize) {
        super(price);
        this.groupSize = groupSize;
    }
}

public class FestWideTicketSystem {
    static String processNightlySettlement(FestEventTicket[] tickets) {
        int processed = 0, skipped = 0, group = 0, individual = 0;

        for (FestEventTicket t : tickets) {
            if (t == null) {
                skipped++;
            } else {
                processed++;

                if (t instanceof GroupTicket)
                    group++;
                else
                    individual++;
            }
        }

        return processed + " processed | " +
                skipped + " null skipped | " +
                group + " group | " +
                individual + " individual";
    }

    public static void main(String[] args) {
        FestEventTicket t1 = new FestEventTicket(500);

        System.out.println("Ticket ID: TCK-1001");
        System.out.println(FestEventTicket.isValidPromoCode("F123A"));
        System.out.println(FestEventTicket.isValidPromoCode("F12A"));
        System.out.println(FestEventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println("Balance: " + t1.getBalanceDue());

        FestEventTicket[] tickets = {
                new GroupTicket(2000, 5),
                null,
                new FestEventTicket(500)
        };

        System.out.println(
                FestEventTicket.getTicketsIssued());

        System.out.println(
                processNightlySettlement(tickets));
    }
}