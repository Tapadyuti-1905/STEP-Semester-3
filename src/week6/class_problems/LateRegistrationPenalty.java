package week6.class_problems;

import java.util.Arrays;

class LateEventTicket {
    protected double balance;
    private double[] history = new double[10];
    private int count = 0;

    LateEventTicket(double price) {
        balance = price;
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

class LateWorkshopTicket extends LateEventTicket {
    LateWorkshopTicket(double price) {
        super(price);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateRegistrationPenalty {
    public static void main(String[] args) {
        LateWorkshopTicket w = new LateWorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println("Balance: " + w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        history[0] = 999;

        System.out.println("History: " +
                Arrays.toString(w.getLateFeeHistory()));
    }
}