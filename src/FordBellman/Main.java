package FordBellman;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FordBellman fordBellman = FordBellman.createWithMatrixStorage();

        try {
            fordBellman.findShortestPath(109, 609);
        } catch (NegativeCycle e) {
            System.out.println(e.getMessage());
        }
    }
}
