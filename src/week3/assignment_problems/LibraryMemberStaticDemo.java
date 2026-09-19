package week3.assignment_problems;

public class LibraryMemberStaticDemo {

    static class BrokenLibraryMember {
        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId,
                            int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    static class LibraryMember {
        String name;
        String memberId;
        int booksIssued;

        static String libraryName = "SRM Library";
        static int memberCount = 0;

        LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;

            memberCount++;
            memberId = "LM-100" + memberCount;
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember a =
                new BrokenLibraryMember("Aditi", "LM-1", 2);

        BrokenLibraryMember b =
                new BrokenLibraryMember("Rohan", "LM-2", 3);

        System.out.println(a.name);
        System.out.println(b.name);

        System.out.println("\nFixed version:");

        LibraryMember m1 =
                new LibraryMember("Aditi", 2);

        LibraryMember m2 =
                new LibraryMember("Rohan", 3);

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}