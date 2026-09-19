package week6.assignment_problems;

class EliteRunnerEntry extends RunnerEntry {
    double sponsorBonus;

    EliteRunnerEntry(String bib, double fee,
                     String category, double bonus) {
        super(bib, fee, category);
        sponsorBonus = bonus;
    }

    void announce() {
        System.out.println("Elite Runner | Bib: " + bibNumber +
                " | Category: " + category +
                " | Sponsor Bonus: " + sponsorBonus +
                " | Balance: " + balanceDue);
    }
}

class RelayTeamEntry extends RaceEntry {
    int teamSize;

    RelayTeamEntry(String bib, double fee, int teamSize) {
        super(bib, fee);
        this.teamSize = teamSize;
    }

    void announce() {
        System.out.println("Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + balanceDue);
    }
}

class BasicRunnerEntry extends RunnerEntry {
    BasicRunnerEntry(String bib, double fee, String category) {
        super(bib, fee, category);
    }

    void announce() {
        System.out.println("Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + balanceDue);
    }
}

public class ThreeShapesRaceFamily {

    static String classifyGeneration(RaceEntry e) {
        if (e instanceof EliteRunnerEntry)
            return "Multilevel descendant (3 generations deep)";

        if (e instanceof RelayTeamEntry)
            return "Hierarchical sibling (independent branch)";

        return "Base generation";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;

        for (RaceEntry e : entries)
            total += e.getBalanceDue();

        return total;
    }

    public static void main(String[] args) {
        BasicRunnerEntry runner =
                new BasicRunnerEntry("BIB2001", 80, "Open 10K");

        EliteRunnerEntry elite =
                new EliteRunnerEntry(
                        "BIB3001", 150,
                        "Elite Full Marathon", 500);

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        runner.announce();
        elite.announce();
        relay.announce();

        System.out.println(
                classifyGeneration(elite));

        System.out.println(
                classifyGeneration(relay));

        RaceEntry[] entries = {runner, elite, relay};

        System.out.println("Total Balance: " +
                getTotalBalanceDue(entries));
    }
}