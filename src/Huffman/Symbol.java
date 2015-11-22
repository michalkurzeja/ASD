package Huffman;

import java.io.Serializable;

public class Symbol implements Serializable {
    public String string;
    public int frequency;

    public Symbol(String string, int frequency) {
        this.string = string;
        this.frequency = frequency;
    }
}
