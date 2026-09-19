package week3.class_problems;

public class SrmStudentStaticDemo {

    static class BrokenStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenStudent(String name, String regNo, int attendance) {
            BrokenStudent.name = name;
            BrokenStudent.regNo = regNo;
            BrokenStudent.attendance = attendance;
        }
    }

    static class SrmStudent {
        String name, regNo;
        int attendance;

        static String university = "SRM University";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = "RA2311003010" + admissionCount;
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: "
                    + admissionCount);
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenStudent s1 =
                new BrokenStudent("Ravi", "R1", 80);
        BrokenStudent s2 =
                new BrokenStudent("Meera", "R2", 90);

        System.out.println(s1.name);
        System.out.println(s2.name);

        System.out.println("\nFixed version:");

        SrmStudent a = new SrmStudent("Ravi", 82);
        SrmStudent b = new SrmStudent("Meera", 75);

        a.printIdCard();
        b.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}