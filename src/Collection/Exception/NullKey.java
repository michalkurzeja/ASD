package Collection.Exception;

public class NullKey  extends Exception {
    public NullKey() {
        super("Element must be an object, null received");
    }
}
