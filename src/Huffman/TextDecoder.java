package Huffman;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import Huffman.HuffmanTree.Node;

public class TextDecoder {

    public void decode(InputStream input, OutputStream output) throws Exception {
        InputStream bufferedInput = new BufferedInputStream(input);

        ObjectInput objectInput = new ObjectInputStream(bufferedInput);
        HuffmanTree tree = (HuffmanTree) objectInput.readObject();
        int inputLength = (int) objectInput.readObject();
        BitSet bits = (BitSet) objectInput.readObject();

        OutputStream bufferedOutput = new BufferedOutputStream(output);

        Node current = tree.getRoot();

        for (int i=0; i<inputLength; i++) {
            current = bits.get(i)
                    ? current.getRightChild()
                    : current.getLeftChild();

            if (current.isLeaf()) {
                bufferedOutput.write(current.getSymbol().string.getBytes(StandardCharsets.UTF_8));
                current = tree.getRoot();
            }
        }

        bufferedOutput.flush();
    }
}
