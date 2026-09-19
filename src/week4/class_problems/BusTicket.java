package week4.class_problems;

import java.util.HashSet;

class BusTicket {
    String passengerName;
    String destination;
    boolean checkedIn = false;

    BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty()
                || !passengerName.matches("[a-zA-Z ]+"))
            throw new IllegalArgumentException("Invalid passenger name");

        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Invalid destination");

        this.passengerName = passengerName;
        this.destination = destination;
    }

    void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in: " + passengerName);
        } else {
            System.out.println("Already checked in: " + passengerName);
        }
    }

    static void processBatch(String[][] rawBookings) {
        HashSet<String> bookings = new HashSet<>();
        int valid = 0, rejected = 0, duplicate = 0;

        for (String[] b : rawBookings) {
            try {
                BusTicket ticket = new BusTicket(b[0], b[1]);
                String key = b[0].trim().toLowerCase() + "|" +
                        b[1].trim().toLowerCase();

                if (bookings.contains(key)) {
                    duplicate++;
                } else {
                    bookings.add(key);
                    valid++;
                }
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
        System.out.println("Duplicates skipped: " + duplicate);
    }

    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(bookings);

        BusTicket ticket = new BusTicket("Divya", "Chennai");
        ticket.markCheckedIn();
        ticket.markCheckedIn();
    }
}