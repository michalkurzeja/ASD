package Collection.HashMap.Test;

import Collection.HashMap.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        HashMap<String, String> mapString = new HashMap<String, String>();

        map.set(1, "1").set(15, "15").set(3, "3").set(51, "51").set(1, "New 1");
        mapString.set("Key1", "Key1").set("Key2", "Key2").set("Key4", "Key4").set("Key51", "Key51");

        mapString.remove("Key4");
    }
}
