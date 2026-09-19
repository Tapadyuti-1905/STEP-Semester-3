package week3.class_problems;

public class FeeHostelMiniSystem {

    static class SrmStudent {
        String name, regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        SrmStudent(String name, String regNo,
                   HostelFeeAccount feeAccount,
                   HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        String fullStatus() {
            String roomNo = (room == null)
                    ? "unallotted"
                    : room.roomNo;

            return name + " | Due: Rs " + feeAccount.getDue()
                    + " | Room: " + roomNo;
        }
    }

    public static void main(String[] args) {

        HostelRoom r1 = new HostelRoom("C-214", 1, 0);
        HostelRoom r2 = new HostelRoom("C-507", 1, 0);

        HostelFeeAccount f1 =
                new HostelFeeAccount("R1", 200000);
        HostelFeeAccount f2 =
                new HostelFeeAccount("R2", 200000);
        HostelFeeAccount f3 =
                new HostelFeeAccount("R3", 200000);

        f1.pay(60000);
        f2.pay(20000);
        f3.pay(-5000);   // rejected

        SrmStudent s1 =
                new SrmStudent("Ravi", "R1", f1, r1);
        SrmStudent s2 =
                new SrmStudent("Anitha", "R2", f2, r2);
        SrmStudent s3 =
                new SrmStudent("Karthik", "R3", f3, null);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());

        System.out.println("Total students: "
                + SrmStudent.totalStudents);
    }
}