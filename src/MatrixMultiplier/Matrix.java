package MatrixMultiplier;

public class Matrix {
    private double [][] matrix;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        matrix = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public double get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, double value) {
        matrix[i][j] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("");

        for (int i=0; i<rows; i++) {
            sb.append("|\t");

            for (int j=0; j<cols; j++) {
                sb.append(matrix[i][j]).append("\t");
            }

            sb.append("|\r\n");
        }

        return sb.toString();
    }
}
