package MatrixMultiplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Loader {

    public static List<Matrix> load(InputStream inputStream) throws IOException {
        BufferedReader bufferedInput = new BufferedReader(new InputStreamReader(inputStream));
        List<Matrix> matrixList = new LinkedList<>();

        String line;
        List<String[]> matrixData = new LinkedList<>();

        while ((line = bufferedInput.readLine()) != null) {
            if (line.isEmpty()) {
                matrixList.add(createMatrixFromData(matrixData));
                matrixData = new LinkedList<>();
                continue;
            }

            matrixData.add(line.split("; "));
        }

        return matrixList;
    }

    private static Matrix createMatrixFromData(List<String[]> matrixData) {
        int rows = matrixData.size();
        int cols = matrixData.get(0).length;

        Matrix matrix = new Matrix(rows, cols);

        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                matrix.set(i, j, Double.parseDouble(matrixData.get(i)[j]));
            }
        }

        return matrix;
    }
}
