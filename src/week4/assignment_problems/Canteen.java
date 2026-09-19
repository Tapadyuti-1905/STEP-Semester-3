package week4.assignment_problems;

public class Canteen {

    String canteenCode, canteenName;
    int trustScore;

    Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {

        // Higher trust score first
        if (trustScore != other.trustScore)
            return Integer.compare(other.trustScore, trustScore);

        // Code comparison ignoring case
        int code =
                canteenCode.compareToIgnoreCase(other.canteenCode);

        if (code != 0)
            return code;

        // Shorter name first
        return Integer.compare(
                canteenName.length(),
                other.canteenName.length());
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = canteens.clone();

        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0; j < result.length - 1 - i; j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        for (Canteen c : ranked)
            System.out.println(c.canteenCode);
    }
}