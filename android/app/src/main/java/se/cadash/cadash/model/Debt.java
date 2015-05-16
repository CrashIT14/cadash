package se.cadash.cadash.model;

/**
 * @author Alexander Håkansson
 */
public class Debt {
    private double sum;

    public Debt() {
        this(0.0);
    }

    public Debt(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public void addToSum(double difference) {
        this.sum += difference;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
