package week3.class_problems;

class FeeAccount {
    private String regNo;
    private double totalFee, amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

public class FeeAccountInheritance {

    public static void main(String[] args) {

        FeeAccount plain = new FeeAccount("R1", 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("R2", 200000);
        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("R3", 180000, 20);

        plain.pay(150000);
        hostel.payInTwoInstallments(60000);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount account : accounts) {

            if (account instanceof ScholarshipFeeAccount) {
                System.out.println("Scholarship account effective due: Rs "
                        + ((ScholarshipFeeAccount) account).effectiveDue());

            } else if (account instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs "
                        + account.getDue());

            } else {
                System.out.println("Plain account due: Rs "
                        + account.getDue());
            }
        }
    }
}