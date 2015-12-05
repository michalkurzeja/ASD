package MatrixMultiplier;

import MatrixMultiplier.Multiplier.MultiplicationStrategy;
import MatrixMultiplier.Multiplier.Multiplier;
import MatrixMultiplier.Multiplier.SingleThreadedStrategy;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        MultiplicationStrategy strategy = new SingleThreadedStrategy();
        Multiplier multiplier = new Multiplier(strategy);

        InputStream matrixInput = new FileInputStream("sample-matrices.txt");
        List<Matrix> matrixList = Loader.load(matrixInput);
        matrixInput.close();

        Matrix product = multiplier.multiply(matrixList);

        System.out.println(product);

//        Matrix M1 = new Matrix(3, 2);
//        Matrix M2 = new Matrix(2, 3);
//        Matrix product;
//
//        M1.set(0,0,1);
//        M1.set(0,1,2);
//        M1.set(1,0,3);
//        M1.set(1,1,4);
//        M1.set(2,0,5);
//        M1.set(2,1,6);
//
//        System.out.println(M1);
//
//        M2.set(0,0,1);
//        M2.set(0,1,2);
//        M2.set(0,2,3);
//        M2.set(1,0,4);
//        M2.set(1,1,5);
//        M2.set(1,2,6);
//
//        System.out.println(M2);
//
//        List<Matrix> matrixList = new LinkedList<>();
//        matrixList.add(M1);
//        matrixList.add(M2);
//
//        product = multiplier.multiply(matrixList);
//
//        System.out.println(product);
    }
}
