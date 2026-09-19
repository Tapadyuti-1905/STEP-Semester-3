package week5.class_problems;

public class ImmutableDischargeSummary {

    static class DischargeSummary {
        private final String patientId;
        private final String[] medicationCodes;

        static {
            System.out.println("Discharge system initialized");
        }

        public DischargeSummary(String patientId, String[] codes) {

            if (codes == null)
                throw new IllegalArgumentException("construction rejected");

            for (String code : codes) {
                if (code == null || !code.matches("MED-[A-Z]"))
                    throw new IllegalArgumentException("construction rejected");
            }

            this.patientId = patientId;
            this.medicationCodes = codes.clone();
        }

        public String[] getMedicationCodes() {
            return medicationCodes.clone();
        }

        public DischargeSummary withCorrectedMedication(
                int index, String newCode) {

            if (!newCode.matches("MED-[A-Z]"))
                throw new IllegalArgumentException("Invalid code");

            String[] copy = medicationCodes.clone();
            copy[index] = newCode;

            return new DischargeSummary(patientId, copy);
        }
    }

    static class CriticalCareDischargeSummary
            extends DischargeSummary {

        private final int icuDays;

        public CriticalCareDischargeSummary(
                String patientId, String[] codes, int icuDays) {

            super(patientId, codes);
            this.icuDays = icuDays;
        }
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int skipped = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary s : summaries) {

            if (s == null) {
                skipped++;
                continue;
            }

            processed++;

            if (s instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | " +
                skipped + " null skipped | " +
                critical + " critical-care | " +
                routine + " routine";
    }

    public static void main(String[] args) {

        DischargeSummary d =
                new DischargeSummary(
                        "MT2026-0142",
                        new String[]{"MED-A", "MED-B"});

        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";

        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] batch = {
                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"}, 4),
                null,
                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"})
        };

        System.out.println(processNightlyBatch(batch));
    }
}