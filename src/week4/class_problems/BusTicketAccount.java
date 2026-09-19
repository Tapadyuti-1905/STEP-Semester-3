package week4.class_problems;

public class BusTicketAccount {

    static double penaltyRate;

    static {
        penaltyRate = 1.0;
    }

    String bookingId;
    double ticketFare;

    BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0)
            throw new IllegalArgumentException("Invalid fare");

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0)
            throw new IllegalArgumentException("Invalid delay");

        return minutesLate * ticketFare * penaltyRate / 100;
    }

    void processAccount(BusTicketAccount account,
                        double amount, int minutesLate) {

        double settledAmount;

        if (account instanceof SleeperAccount) {
            settledAmount = amount * 0.90;
        } else {
            settledAmount = amount;
        }

        double penalty = account.calculatePenalty(minutesLate);

        System.out.println(account.bookingId
                + " | Settled: Rs " + settledAmount
                + " | Penalty: Rs " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double totalPenalty = 0;

        int n = Math.min(accounts.length,
                Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < n; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double penalty =
                    accounts[i].calculatePenalty(minutesLateArray[i]);

            if (accounts[i] instanceof SleeperAccount)
                sleeper++;
            else
                regular++;

            totalPenalty += penalty;
            processed++;

            double settled = amounts[i];

            if (accounts[i] instanceof SleeperAccount)
                settled *= 0.90;

            System.out.println(accounts[i].bookingId
                    + " | Settled: Rs " + settled
                    + " | Penalty: Rs " + penalty);
        }

        System.out.println(processed + " processed | "
                + nullSkipped + " null skipped | "
                + sleeper + " sleeper | "
                + regular + " regular");

        System.out.println("Grand total penalties = Rs "
                + totalPenalty);
    }

    static class SleeperAccount extends BusTicketAccount {

        SleeperAccount(String id, double fare) {
            super(id, fare);
        }
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
                new SleeperAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLate = {10, 5, 0};

        processBatch(accounts, amounts, minutesLate);
    }
}