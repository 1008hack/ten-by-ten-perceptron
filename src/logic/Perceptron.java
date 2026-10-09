package logic;

public class Perceptron {
    private int weight;
    Perceptron() {
        this.weight = 0;
    }

    public int getWeight() {
        return weight;
    }

    public void boost() {
        weight++;
    }

    public void stress() {
        weight--;
    }
}
