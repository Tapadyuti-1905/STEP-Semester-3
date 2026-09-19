package week6.class_problems;

class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid attendee ID");

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        balanceDue = basePrice;
    }

    void pay(double amount) {
        balanceDue -= amount;
    }

    double getBalanceDue() {
        return balanceDue;
    }

    void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " + getBalanceDue());
    }

    static String registerBatch(String[] ids, double price) {
        int registered = 0, rejected = 0;

        for (String id : ids) {
            try {
                new EventTicket(id, price);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    String track;

    WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track
                + " | Balance Due: " + getBalanceDue());
    }
}

public class TicketHierarchyFoundation {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);

        System.out.println("Balance: " + w.getBalanceDue());

        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};
        System.out.println(EventTicket.registerBatch(ids, 500));
    }
}