package FordBellman;

public class NegativeCycle extends Exception {
    NegativeCycle() {
        super("Negative-length cycle detected");
    }
}
