import java.util.*;
import java.io.*;

public class GymManagementSystem {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Trainer> trainers = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();

    private final String MEMBER_FILE = "C:\\Java\\GymmanagementSystem\\data\\member.txt";
    private final String PAYMENT_FILE = "C:\\Java\\GymmanagementSystem\\data\\payments.txt";

     Scanner sc = new Scanner(System.in);

    public GymManagementSystem() {
        loadMembers();
        loadPayments();
    }
    public void addMember() {
        try {
            System.out.print("Enter Member ID: ");
            String id = sc.next();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            System.out.print("Enter Membership Type: ");
            String type = sc.next();

            Member m = new Member(id, name, age, type);
            members.add(m);
            saveMembers();
            System.out.println("Member added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter correct values.");
            sc.nextLine();
        }
    }
    public void viewMembers() {
        if (members.isEmpty()) System.out.println("No members found!");
        else for (Member m : members) m.showInfo();
    }
     public void updateMember() {
        System.out.print("Enter Member ID to update: ");
        String id = sc.next();
        for (Member m : members) {
            if (m.getMemberId().equals(id)) {
                System.out.print("Enter new name: ");
                m.name = sc.next();
                saveMembers();
                System.out.println("Member updated!");
                return;
            }
        }
        System.out.println("Member not found!");
    }
    public void deleteMember() {
        System.out.print("Enter Member ID to delete: ");
        String id = sc.next();
        members.removeIf(m -> m.getMemberId().equals(id));
        saveMembers();
        System.out.println("Member deleted!");
    }

    public void markAttendance() {
        System.out.print("Enter Member ID: ");
        String id = sc.next();
        for (Member m : members) {
            if (m.getMemberId().equals(id)) {
                System.out.println(m.name + " marked present today.");
                return;
            }
        }
        System.out.println("Member not found!");
    }

    public void recordPayment() {
        try {
            System.out.print("Enter Payment ID: ");
            String pid = sc.next();
            System.out.print("Enter Member ID: ");
            String mid = sc.next();
            System.out.print("Enter Amount: ");
            double amt = sc.nextDouble();
            System.out.print("Enter Date: ");
            String date = sc.next();

            Payment p = new Payment(pid, mid, amt, date);
            payments.add(p);

            for (Member m : members) {
                if (m.getMemberId().equals(mid)) {
                    m.payDue(amt);
                }
            }

            savePayments();
            saveMembers();
            System.out.println("Payment recorded successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount entered!");
            sc.nextLine();
        }
    }

    public void viewDues() {
        for (Member m : members)
            if (m.getDueAmount() > 0)
                System.out.println(m.getMemberId() + " | " + m.name + " | Due: Rs." + m.getDueAmount());
    }

    public void addTrainer() {
        System.out.print("Enter Trainer ID: ");
        String id = sc.next();
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        trainers.add(new Trainer(id, name, age));
        System.out.println("Trainer added successfully!");
}

    public void viewReports() {
        System.out.println("\n--- Members ---");
        viewMembers();
        System.out.println("\n--- Payments ---");
        for (Payment p : payments) p.printReceipt();
        System.out.println("\n--- Dues ---");
        viewDues();
    }
    private void saveMembers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MEMBER_FILE))) {
            for (Member m : members) {
                bw.write(m.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    private void loadMembers() {
        File file = new File(MEMBER_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Member m = Member.fromFileString(line);
                if (m != null) members.add(m);
            }
        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }
    }

    private void savePayments() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PAYMENT_FILE))) {
            for (Payment p : payments) {
                bw.write(p.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving payments: " + e.getMessage());
        }
    }

    private void loadPayments() {
        File file = new File(PAYMENT_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Payment p = Payment.fromFileString(line);
                if (p != null) payments.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error loading payments: " + e.getMessage());
        }
    }


}