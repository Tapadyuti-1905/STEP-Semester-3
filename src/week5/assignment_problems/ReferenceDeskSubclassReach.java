package week5.assignment_problems;

public class ReferenceDeskSubclassReach {

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

        if (modifier.equals("protected")) {

            if (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE") ||
                    context.equals(
                            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            return "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String context) {

        String[] words = context.split("_");
        String result = "";

        for (String w : words)
            result += w.charAt(0) +
                    w.substring(1).toLowerCase() + " ";

        return result.trim();
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(
                classifyAccess("protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println(
                describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}