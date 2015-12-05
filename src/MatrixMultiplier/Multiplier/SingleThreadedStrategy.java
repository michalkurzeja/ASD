package MatrixMultiplier.Multiplier;

import MatrixMultiplier.Matrix;

public class SingleThreadedStrategy implements MultiplicationStrategy {
    @Override
    public Matrix multiply(Matrix M1, Matrix M2) {
        Matrix product = new Matrix(M1.getRows(), M2.getCols());

        for (int i=0; i<M1.getRows(); i++) {
            for (int j=0; j<M2.getCols(); j++) {
                product.set(i, j, getCellValue(M1, M2, i, j));
            }
        }

        return product;
    }

    private double getCellValue(Matrix M1, Matrix M2, int i, int j) {
        double value = 0;

        for (int k=0; k<M1.getCols(); k++) {
            value += M1.get(i, k) * M2.get(k, j);
        }

        return value;
    }
}
