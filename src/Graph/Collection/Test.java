package Graph.Collection;

public class Test {
    public static void main(String[] args) throws Exception {
        DynamicArray<String> a = new DynamicArray<>();

        a.add("Elem 1");
        a.add("Elem 2");
        a.add("Elem 3");

        boolean status = a.contains("Elem 2");

        int index = a.indexOf("Elem 3");
    }
}
