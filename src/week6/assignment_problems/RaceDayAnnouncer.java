package week6.assignment_problems;

class AnnounceRaceEntry {
    protected String bib;
    protected double balance;

    AnnounceRaceEntry(String bib, double fee) {
        this.bib = bib;
        balance = fee;
    }

    double getBalanceDue() {
        return balance;
    }

    void announce() {
        System.out.print(
                "Race Entry | Bib: " + bib +
                        " | Balance: " + balance);
    }
}

class AnnounceRunnerEntry extends AnnounceRaceEntry {
    String category;

    AnnounceRunnerEntry(String bib, double fee,
                        String category) {
        super(bib, fee);
        this.category = category;
    }

    @Override
    void announce() {
        System.out.print(
                "Runner Entry | Bib: " + bib +
                        " | Category: " + category +
                        " | Balance: " + balance);
    }
}

class AnnounceRelayTeamEntry extends AnnounceRaceEntry {
    int teamSize;

    AnnounceRelayTeamEntry(String bib, double fee,
                           int teamSize) {
        super(bib, fee);
        this.teamSize = teamSize;
    }

    @Override
    void announce() {
        System.out.print(
                "Relay Team | Bib: " + bib +
                        " | Team Size: " + teamSize +
                        " | Balance: " + balance);
    }
}

public class RaceDayAnnouncer {

    static String announceAll(AnnounceRaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();

        for (AnnounceRaceEntry e : entries) {
            if (e instanceof AnnounceRunnerEntry)
                sb.append("Runner Entry | Bib: ")
                        .append(e.bib)
                        .append(" | Category: ")
                        .append(((AnnounceRunnerEntry) e).category)
                        .append(" | Balance: ")
                        .append(e.balance)
                        .append(" | ");

            else if (e instanceof AnnounceRelayTeamEntry)
                sb.append("Relay Team | Bib: ")
                        .append(e.bib)
                        .append(" | Team Size: ")
                        .append(((AnnounceRelayTeamEntry) e).teamSize)
                        .append(" | Balance: ")
                        .append(e.balance)
                        .append(" [Team size via downcast: ")
                        .append(((AnnounceRelayTeamEntry) e).teamSize)
                        .append("] | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        AnnounceRaceEntry runner =
                new AnnounceRunnerEntry(
                        "BIB2001", 90, "Open 10K");

        AnnounceRaceEntry relay =
                new AnnounceRelayTeamEntry(
                        "BIB4001", 300, 4);

        System.out.println(
                announceAll(
                        new AnnounceRaceEntry[]{runner, relay}));
    }
}