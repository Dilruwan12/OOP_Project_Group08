public class Payment {
    private String paymentId;
    private String memberId;
    private double amount;
    private String date;

    public Payment(String paymentId, String memberId, double amount, String date) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.date = date;
    }

    public String toFileString() {
        return paymentId + "," + memberId + "," + amount + "," + date;
    }

    public static Payment fromFileString(String line) {
        String[] data = line.split(",");
        if (data.length >= 4) {
            return new Payment(data[0], data[1], Double.parseDouble(data[2]), data[3]);
        }
        return null;
    }

}