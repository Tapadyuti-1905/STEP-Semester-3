package week6.assignment_problems;

class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException("Invalid bib number");

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        balanceDue = entryFee;
    }

    void pay(double amount) {
        balanceDue -= amount;
    }

    double getBalanceDue() {
        return balanceDue;
    }

    static String registerBatch(String[] bibs, double fee) {
        int registered = 0, rejected = 0;

        for (String bib : bibs) {
            try {
                new RaceEntry(bib, fee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
                " | Rejected: " + rejected;
    }
}

class RunnerEntry extends RaceEntry {
    String category;

    RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}

public class RaceEntryFoundation {
    public static void main(String[] args) {
        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);

        System.out.println("Balance: " +
                r.getBalanceDue());

        String[] bibs = {"BIB1", "B1", "BIB2"};

        System.out.println(
                RaceEntry.registerBatch(bibs, 80));
    }
}