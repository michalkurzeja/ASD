package Huffman;

import java.io.*;
import java.nio.charset.StandardCharsets;
import Huffman.HuffmanTree.Node;

public class TextDecoder {

    public void decode(InputStream input, OutputStream output) throws Exception {
        InputStream bufferedInput = new BufferedInputStream(input);

        ObjectInput objectInput = new ObjectInputStream(bufferedInput);
        HuffmanTree tree = (HuffmanTree) objectInput.readObject();

        OutputStream bufferedOutput = new BufferedOutputStream(output);

        int read;
        char c;
        Node current = tree.getRoot();

        while (0 <= (read = bufferedInput.read())) {
            c = (char) read;

            current = c == '0'
                    ? current.getLeftChild()
                    : current.getRightChild();

            if (current.isLeaf()) {
                bufferedOutput.write(current.getSymbol().string.getBytes(StandardCharsets.UTF_8));
                current = tree.getRoot();
            }
        }

        bufferedOutput.flush();
    }
}
