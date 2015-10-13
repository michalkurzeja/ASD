package Collection.Exception;

public class KeyNotFound extends Exception {
    public KeyNotFound(Object key) {
        super(String.format("Key \"%s\" was not found", key.toString()));
    }
}
