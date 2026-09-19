package week6.class_problems;

class PremiumWorkshopTicket extends WorkshopTicket {
    double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice,
                                 String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    void printTicket() {
        System.out.println("Premium Workshop Ticket | Track: " + track
                + " | Kit Fee: " + kitFee
                + " | Balance Due: " + getBalanceDue());
    }
}

class HackathonTicket extends EventTicket {
    String teamName;

    public HackathonTicket(String attendeeId, double price, String teamName) {
        super(attendeeId, price);
        this.teamName = teamName;
    }

    @Override
    void printTicket() {
        System.out.println("Hackathon Ticket | Team: " + teamName
                + " | Balance Due: " + getBalanceDue());
    }
}

public class ThreeShapesFamilyTree {

    static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";

        if (ticket instanceof HackathonTicket)
            return "Hierarchical sibling (independent branch)";

        return "Standard Event Ticket";
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0;

        for (EventTicket t : tickets)
            total += t.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {

        EventTicket standardTicket =
                new EventTicket("STU1", 500);

        WorkshopTicket workshopTicket =
                new WorkshopTicket("STU2", 1200, "AI/ML");

        PremiumWorkshopTicket premiumTicket =
                new PremiumWorkshopTicket("STU3", 2000,
                        "Cloud Native", 300);

        HackathonTicket hackathonTicket =
                new HackathonTicket("STU4", 800,
                        "Byte Force");

        standardTicket.printTicket();
        workshopTicket.printTicket();
        premiumTicket.printTicket();
        hackathonTicket.printTicket();

        System.out.println();

        System.out.println(classifyGeneration(premiumTicket));
        System.out.println(classifyGeneration(hackathonTicket));

        System.out.println();

        EventTicket[] tickets = {
                standardTicket,
                workshopTicket,
                premiumTicket,
                hackathonTicket
        };

        System.out.println("Grand Total = "
                + getTotalBalanceDue(tickets));
    }
}