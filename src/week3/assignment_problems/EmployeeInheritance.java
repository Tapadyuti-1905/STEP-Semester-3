package week3.assignment_problems;

class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int id, String name,
                    double salary, double teamBonus) {
        super(id, name, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int id, String name,
                   double salary, double stipendCap) {
        super(id, name, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeeInheritance {

    public static void main(String[] args) {

        Employee plain =
                new Employee(1, "A", 40000);

        ManagerEmployee manager =
                new ManagerEmployee(2, "B", 70000, 8000);

        InternEmployee intern =
                new InternEmployee(3, "C", 12000, 10000);

        Employee[] employees = {plain, manager, intern};

        for (Employee e : employees) {

            if (e instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs "
                        + ((ManagerEmployee) e).effectiveSalary());

            } else if (e instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs "
                        + ((InternEmployee) e).effectiveSalary());

            } else {
                System.out.println("Plain employee pay: Rs "
                        + e.getSalary());
            }
        }
    }
}