package MatrixMultiplier.Multiplier;

import MatrixMultiplier.Matrix;

public interface MultiplicationStrategy {
    Matrix multiply(Matrix M1, Matrix M2);
}
