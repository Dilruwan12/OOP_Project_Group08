public class Trainer extends Person {
    private String trainerId;

    public Trainer(String trainerId, String name, int age) {
        super(name, age);
        this.trainerId = trainerId;
    }

    public void showInfo() {
        System.out.println("Trainer: " + name + " (" + trainerId + ")");
    }
}
