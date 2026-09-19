package week5.class_problems;

import java.util.ArrayList;

public class VitalsMonitoringEncapsulationGuard {

    static class PatientVitals {
        private ArrayList<Double> readings = new ArrayList<>();

        PatientVitals(double[] initialReadings) {
            for (double r : initialReadings)
                recordReading(r);
        }

        void recordReading(double reading) {
            if (reading > 0 && reading <= 45)
                readings.add(reading);
        }

        double getAverage() {
            double sum = 0;

            for (double r : readings)
                sum += r;

            return readings.isEmpty() ? 0 : sum / readings.size();
        }

        double[] getAllReadings() {
            double[] result = new double[readings.size()];

            for (int i = 0; i < readings.size(); i++)
                result[i] = readings.get(i);

            return result;
        }
    }

    public static void main(String[] args) {

        PatientVitals v =
                new PatientVitals(new double[]{36.5, -2, 37.1});

        for (double x : v.getAllReadings())
            System.out.print(x + " ");

        System.out.println();

        double[] copy = v.getAllReadings();
        copy[0] = 999;

        System.out.println(v.getAllReadings()[0]);
    }
}