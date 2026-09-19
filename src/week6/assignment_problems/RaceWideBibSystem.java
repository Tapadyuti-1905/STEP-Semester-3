package week6.assignment_problems;

class BibRaceEntry {
    private static int counter = 0;
    private final String entryCode;
    protected double balance;

    BibRaceEntry(String bib, double fee) {
        counter++;
        entryCode = "ENT-" + counter;
        balance = fee;
    }

    void pay(double amount) {
        balance -= amount;
    }

    void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'M'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    static int getBibCounter() {
        return counter;
    }
}

class BibRelayTeamEntry extends BibRaceEntry {
    int teamSize;

    BibRelayTeamEntry(String bib, double fee, int teamSize) {
        super(bib, fee);
        this.teamSize = teamSize;
    }
}

public class RaceWideBibSystem {

    static String settleNight(BibRaceEntry[] entries) {
        int processed = 0;
        int skipped = 0;
        int relay = 0;
        int individual = 0;

        for (BibRaceEntry e : entries) {
            if (e == null) {
                skipped++;
            } else {
                processed++;

                if (e instanceof BibRelayTeamEntry)
                    relay++;
                else
                    individual++;
            }
        }

        return processed + " processed | " +
                skipped + " null skipped | " +
                relay + " relay | " +
                individual + " individual";
    }

    public static void main(String[] args) {

        System.out.println(
                BibRaceEntry.isValidDiscountCode("M123A"));

        System.out.println(
                BibRaceEntry.isValidDiscountCode("M12A"));

        System.out.println(
                BibRaceEntry.isValidDiscountCode("X123A"));

        BibRaceEntry r =
                new BibRaceEntry("BIB1001", 50);

        r.pay(10, "UPI");

        BibRaceEntry relay =
                new BibRelayTeamEntry("BIB2001", 300, 4);

        BibRaceEntry[] entries = {
                r, null, relay
        };

        System.out.println(
                settleNight(entries));

        System.out.println(
                "Entries issued: " +
                        BibRaceEntry.getBibCounter());
    }
}