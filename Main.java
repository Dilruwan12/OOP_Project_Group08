import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GymManagementSystem gym = new GymManagementSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== GYM MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Mark Attendance");
            System.out.println("6. Record Payment");
            System.out.println("7. View Dues");
            System.out.println("8. Add Trainer");
            System.out.println("9. View Reports");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> gym.addMember();
                case 2 -> gym.viewMembers();
                case 3 -> gym.updateMember();
                case 4 -> gym.deleteMember();
                case 5 -> gym.markAttendance();
                case 6 -> gym.recordPayment();
                case 7 -> gym.viewDues();
                case 8 -> gym.addTrainer();
                case 9 -> gym.viewReports();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (true);
    }
}
