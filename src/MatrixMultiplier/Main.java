package MatrixMultiplier;

import MatrixMultiplier.Multiplier.MultiplicationStrategy;
import MatrixMultiplier.Multiplier.Multiplier;
import MatrixMultiplier.Multiplier.SingleThreadedStrategy;

import java.io.FileInputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        MultiplicationStrategy strategy = new SingleThreadedStrategy();
        Multiplier multiplier = new Multiplier(strategy);

        List<Matrix> matrixList = Loader.load(new FileInputStream("sample-matrices.txt"));

        Matrix product = multiplier.multiply(matrixList);

        System.out.println(product);

//        Matrix M1 = new Matrix(2, 2);
//        Matrix M2 = new Matrix(2, 2);
//        Matrix product;
//
//        M1.set(0,0,1);
//        M1.set(0,1,2);
//        M1.set(1,0,3);
//        M1.set(1,1,4);
//
//        M2.set(0,0,1);
//        M2.set(0,1,2);
//        M2.set(1,0,3);
//        M2.set(1,1,4);
//
//        = new LinkedList<>();
//        matrixList.add(M1);
//        matrixList.add(M2);
//
//



    }
}
