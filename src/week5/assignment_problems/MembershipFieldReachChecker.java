package week5.assignment_problems;

public class MembershipFieldReachChecker {

    static String classifyAccess(String modifier, String context) {

        if (modifier.equals("public"))
            return "ALLOWED";

        if (modifier.equals("private"))
            return context.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected"))
            return context.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers =
                {"private", "default", "protected", "public"};

        String result = "";

        for (String m : modifiers) {
            int allowed = 0, denied = 0;

            for (String[] a : attempts) {
                if (a[0].equals(m)) {
                    if (classifyAccess(a[0], a[1]).equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            result += m + ": " + allowed +
                    " allowed / " + denied + " denied | ";
        }

        return result.substring(0, result.length() - 3);
    }

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String id, String branch,
                             double fines, String name) {

            if (id == null || id.trim().length() < 4)
                throw new IllegalArgumentException(
                        "construction rejected");

            membershipId = id;
            branchCode = branch;
            finesOwed = fines;
            displayName = name;
        }
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS"));

        System.out.println(
                classifyAccess("protected",
                        "DIFFERENT_PACKAGE"));

        String[][] data = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(data));

        try {
            new LibraryMember(
                    "LB9", "BR1", 0, "Priya Nair");
        } catch (Exception e) {
            System.out.println("construction rejected");
        }
    }
}