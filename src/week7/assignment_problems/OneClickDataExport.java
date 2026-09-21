package week7.assignment_problems;

interface Exportable {

    String exportData();

    static int getTotalExports() {
        return ExportCounter.count;
    }
}

class ExportCounter {
    static int count = 0;
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportCounter.count++;
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportCounter.count++;
        return "Exported profile: " + username;
    }
}

public class OneClickDataExport {

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {

        ReportGenerator r =
                new ReportGenerator("Sales Q1");

        UserProfile u =
                new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        Exportable ref = r;

        System.out.println();
        exportAll(new Exportable[]{ref, u});

        System.out.println();
        System.out.println("Total Exports = "
                + Exportable.getTotalExports());
    }
}