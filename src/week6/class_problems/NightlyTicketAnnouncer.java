package week6.class_problems;

class AnnounceEventTicket {
    protected double balance;

    AnnounceEventTicket(double price) {
        balance = price;
    }

    double getBalanceDue() {
        return balance;
    }

    void printTicket() {
        System.out.print("Standard | Balance: " + balance);
    }
}

class AnnounceWorkshopTicket extends AnnounceEventTicket {
    String track;

    AnnounceWorkshopTicket(double price, String track) {
        super(price);
        this.track = track;
    }

    @Override
    void printTicket() {
        System.out.print("Workshop | Track: " + track +
                " | Balance: " + balance);
    }

    String getTrack() {
        return track;
    }
}

public class NightlyTicketAnnouncer {
    static String batchPrint(AnnounceEventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (AnnounceEventTicket t : tickets) {
            t.printTicket();

            if (t instanceof AnnounceWorkshopTicket) {
                AnnounceWorkshopTicket w =
                        (AnnounceWorkshopTicket) t;

                sb.append("[").append(w.getTrack()).append("] ");
            } else {
                sb.append("Standard ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        AnnounceEventTicket[] tickets = {
                new AnnounceEventTicket(500),
                new AnnounceWorkshopTicket(1200, "AI/ML")
        };

        System.out.println(batchPrint(tickets));
    }
}