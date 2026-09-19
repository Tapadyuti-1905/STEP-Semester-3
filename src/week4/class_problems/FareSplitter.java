package week4.class_problems;

import java.util.Arrays;

public class FareSplitter {

    String tripId;
    double totalFare;
    int passengerCount;

    FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException("Invalid fare or passengers");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    double[] fareBreakdown() {
        double[] result = new double[passengerCount];

        if (totalFare == 0)
            return result;

        double share = Math.floor(totalFare / passengerCount * 100)
                / 100.0;

        double sum = share * passengerCount;
        double remainder = Math.round((totalFare - sum) * 100) / 100.0;

        for (int i = 0; i < passengerCount; i++)
            result[i] = share;

        result[passengerCount - 1] += remainder;

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter f1 =
                new FareSplitter("TRIP001", 100000, 3);

        FareSplitter f2 =
                new FareSplitter("TRIP003");

        System.out.println(Arrays.toString(f1.fareBreakdown()));
        System.out.println(Arrays.toString(f2.fareBreakdown()));

        System.out.println("Overdue: "
                + f1.isConfirmationOverdue(2, 3));
    }
}