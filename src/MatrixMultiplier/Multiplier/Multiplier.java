package MatrixMultiplier.Multiplier;

import MatrixMultiplier.Matrix;

import java.util.Iterator;
import java.util.List;

public class Multiplier {

    private MultiplicationStrategy strategy;

    public Multiplier(MultiplicationStrategy strategy) {
        this.strategy = strategy;
    }

    public Matrix multiply(List<Matrix> matrixList) throws Exception {
        Iterator<Matrix> iterator = matrixList.iterator();
        Matrix matrix = iterator.next();

        while (iterator.hasNext()) {
            matrix = multiply(iterator.next(), matrix);
        }

        return matrix;
    }

    public Matrix multiply(Matrix M1, Matrix M2) throws Exception {
        if (M1.getRows() != M2.getCols()) {
            throw new Exception("Matrix dimensions do not match!");
        }

        return strategy.multiply(M1, M2);
    }
}
