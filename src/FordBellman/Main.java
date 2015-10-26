package FordBellman;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FordBellman fordBellmanMatrix = FordBellman.createWithMatrixStorage();

        System.out.println("Matrix storage:");
        try {
            fordBellmanMatrix.findShortestPath(109, 609);
        } catch (NegativeCycle e) {
            System.out.println(e.getMessage());
        }

        FordBellman fordBellmanList = FordBellman.createWithMatrixStorage();

        System.out.println();
        System.out.println("List storage:");
        try {
            fordBellmanList.findShortestPath(109, 609);
        } catch (NegativeCycle e) {
            System.out.println(e.getMessage());
        }
    }
}
