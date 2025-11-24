public class Member extends Person {
    private String memberId;
    private String membershipType;
    private double dueAmount;

    public Member(String memberId, String name, int age, String membershipType) {
        super(name, age);
        this.memberId = memberId;
        this.membershipType = membershipType;
        this.dueAmount = 0;
    }

    public String getMemberId() { return memberId; }
    public String getMembershipType() { return membershipType; }
    public double getDueAmount() { return dueAmount; }

    public void addDue(double amount) { dueAmount += amount; }
    public void payDue(double amount) { dueAmount = Math.max(0, dueAmount - amount); }

    @Override
    public void showInfo() {
        System.out.println("Member ID: " + memberId + " | Name: " + name +
                           " | Age: " + age + " | Type: " + membershipType +
                           " | Due: Rs." + dueAmount);
    }

    public String toFileString() {
        return memberId + "," + name + "," + age + "," + membershipType + "," + dueAmount;
    }

    public static Member fromFileString(String line) {
        String[] data = line.split(",");
        if (data.length >= 5) {
            Member m = new Member(data[0], data[1], Integer.parseInt(data[2]), data[3]);
            m.dueAmount = Double.parseDouble(data[4]);
            return m;
        }
        return null;
    }
}
