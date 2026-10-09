package logic;

public class Grid {
    private Perceptron[][] perceptrons;
    private boolean[][] input;

    public Grid() {
        perceptrons = new Perceptron[10][10];
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) perceptrons[i][j] = new Perceptron();
        input = new boolean[10][10];
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) input[i][j] = false;
    }

    public boolean getCurrentStateOf(int x, int y) {
        return input[x][y];
    }

    public void setCurrentStateOf(int x, int y, boolean state) {
        input[x][y] = state;
    }

    public void resetInputGrid() {
        input = new boolean[10][10];
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) input[i][j] = false;
    }

    public int expectation() {
        int totalScore = 0;
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) if(input[i][j]) totalScore += perceptrons[i][j].getWeight();
        return totalScore;
    }

    public void boost() {
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) if(input[i][j]) perceptrons[i][j].boost();
    }

    public void stress() {
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) if(input[i][j]) perceptrons[i][j].stress();
    }
}
