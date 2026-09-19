package week6.assignment_problems;

import java.util.Arrays;

class BaseRaceEntry {
    protected double balance;
    private double[] history = new double[10];
    private int count = 0;

    BaseRaceEntry(String bib, double fee) {
        balance = fee;
    }

    void pay(double amount) {
        balance -= amount;
    }

    protected void applyLateFee(double amount) {
        balance += amount;
        history[count++] = amount;
    }

    double getBalanceDue() {
        return balance;
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(history, count);
    }
}

class LateRunnerEntry extends BaseRaceEntry {

    LateRunnerEntry(String bib, double fee, String category) {
        super(bib, fee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalPenalty {
    public static void main(String[] args) {
        LateRunnerEntry r =
                new LateRunnerEntry(
                        "BIB2001", 80, "Open 10K");

        r.pay(30);
        r.applyLateFee(20);

        System.out.println(
                "Balance: " + r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        history[0] = 999;

        System.out.println(
                "History: " +
                        Arrays.toString(
                                r.getLateFeeHistory()));
    }
}