package week4.assignment_problems;

public class DeliveryAccount {

    static double surgeRate;

    static {
        surgeRate = 1.0;
    }

    String studentId;
    double orderValue;

    DeliveryAccount(String studentId, double orderValue) {
        if (orderValue < 0)
            throw new IllegalArgumentException("Invalid order value");

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0)
            throw new IllegalArgumentException("Invalid delay");

        if (delayMinutes == 0)
            return 0;

        double fee = 0;

        int first = Math.min(delayMinutes, 5);
        fee += first * orderValue * 0.005;

        if (delayMinutes > 5) {
            int second = Math.min(delayMinutes, 15) - 5;
            fee += second * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int third = delayMinutes - 15;
            fee += third * orderValue * 0.02;
        }

        double minimum =
                orderValue * surgeRate / 100;

        return Math.max(fee, minimum);
    }

    void processAccount(DeliveryAccount account,
                        double amount, int delayMinutes) {

        double fee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumAccount)
            fee *= 0.90;

        System.out.println(account.studentId
                + " | Surge fee: Rs " + fee);
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double total = 0;

        int n = Math.min(accounts.length,
                Math.min(amounts.length,
                        delayMinutesArray.length));

        for (int i = 0; i < n; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double fee =
                    accounts[i].calculateSurgeFee(delayMinutesArray[i]);

            if (accounts[i] instanceof PremiumAccount) {
                premium++;
                fee *= 0.90;
            } else {
                regular++;
            }

            total += fee;
            processed++;

            System.out.println(accounts[i].studentId
                    + " | Surge fee: Rs " + fee);
        }

        System.out.println(processed + " processed | "
                + nullSkipped + " null skipped | "
                + premium + " premium | "
                + regular + " regular");

        System.out.println("Grand total surge fees = Rs " + total);
    }

    static class PremiumAccount extends DeliveryAccount {

        PremiumAccount(String id, double value) {
            super(id, value);
        }
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
                new PremiumAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};

        processBatch(accounts, amounts, delays);
    }
}