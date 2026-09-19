package week3.assignment_problems;

public class CompanyEmployeeParkingSystem {

    static class Employee {
        String name;
        int empId;
        double salary;

        Employee(String name, int empId, double salary) {
            this.name = name;
            this.empId = empId;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        double teamBonus;

        ManagerEmployee(String name, int empId,
                        double salary, double teamBonus) {
            super(name, empId, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return salary + teamBonus;
        }
    }

    static class ParkingSlot {
        String slotNo;
        int capacity, occupiedCount;

        ParkingSlot(String slotNo, int capacity) {
            this.slotNo = slotNo;
            this.capacity = capacity;
        }

        boolean allot() {
            if (occupiedCount < capacity) {
                occupiedCount++;
                return true;
            }
            return false;
        }
    }

    static class CompanyEmployeeRecord {
        String name;
        String empId;
        Employee employee;
        ParkingSlot slot;

        static int totalRecords = 0;

        CompanyEmployeeRecord(String name, String empId,
                              Employee employee, ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        String fullProfile() {

            double pay;

            if (employee instanceof ManagerEmployee)
                pay = ((ManagerEmployee) employee).effectiveSalary();
            else
                pay = employee.getSalary();

            String slotNo = (slot == null)
                    ? "no parking assigned"
                    : slot.slotNo;

            return name + " | Pay: Rs " + pay
                    + " | Slot: " + slotNo;
        }
    }

    public static void main(String[] args) {

        ParkingSlot a1 = new ParkingSlot("A1", 1);
        ParkingSlot a2 = new ParkingSlot("A2", 1);

        a1.allot();
        a2.allot();

        Employee manager =
                new ManagerEmployee("Divya", 1, 70000, 8000);

        Employee employee =
                new Employee("Karan", 2, 40000);

        Employee intern =
                new Employee("Meera", 3, 10000);

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord("Divya", "E1",
                        manager, a1);

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord("Karan", "E2",
                        employee, a2);

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord("Meera", "E3",
                        intern, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);
    }
}