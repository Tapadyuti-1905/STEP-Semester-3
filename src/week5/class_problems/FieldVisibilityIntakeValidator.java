package week5.class_problems;

public class FieldVisibilityIntakeValidator {

    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("public"))
            return "ALLOWED";

        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return context.equals("SAME_CLASS") || context.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected"))
            return context.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;

        for (String[] a : attempts) {
            if (classifyAccess(a[0], a[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    static class PatientRecord {
        private String patientId;
        String wardCode;
        protected double vitalsScore;
        public String facilityName;

        PatientRecord(String patientId, String wardCode,
                      double vitalsScore, String facilityName) {

            if (patientId == null || patientId.trim().length() < 4)
                throw new IllegalArgumentException("construction rejected");

            this.patientId = patientId;
            this.wardCode = wardCode;
            this.vitalsScore = vitalsScore;
            this.facilityName = facilityName;
        }
    }

    public static void main(String[] args) {

        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] data = {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(data));

        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
        } catch (Exception e) {
            System.out.println("construction rejected");
        }
    }
}