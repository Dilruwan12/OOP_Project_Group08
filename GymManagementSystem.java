import java.util.*;
import java.io.*;
import java.lang.reflect.Member;

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




}