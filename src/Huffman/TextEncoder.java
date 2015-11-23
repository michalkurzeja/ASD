package Huffman;

import java.io.*;
import java.util.BitSet;

public class TextEncoder {

    private HuffmanTree tree;
    private char[] buffer;

    public TextEncoder(HuffmanTree tree, int stringSize) {
        this.tree = tree;
        buffer = new char[stringSize];
    }

    public float encode(InputStream text, OutputStream output) throws Exception {
        float inputLength = 0;
        float compressedLength = 0;

        Reader reader = new InputStreamReader(text);

        OutputStream bufferedOutput = new BufferedOutputStream(output);
        ObjectOutput objectOutput = new ObjectOutputStream(bufferedOutput);

        objectOutput.writeObject(tree);

        StringBuilder builder;
        String code;
        int read;
        BitSet bits = new BitSet();
        int bitIndex = 0;

        while (0 < (read = reader.read(buffer))) {
            inputLength += read;
            builder  = new StringBuilder();
            builder.append(buffer, 0, read);

            code = tree.getStringCode(builder.toString());

            for (char c : code.toCharArray()) {
                bits.set(bitIndex++, c != '0');
            }

            compressedLength += code.length();
        }

        objectOutput.writeObject(bitIndex);
        objectOutput.writeObject(bits);
        objectOutput.flush();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(tree);
        oos.close();

        compressedLength = bitIndex/8 + (bitIndex % 8 > 0 ? 1 : 0) + 1 + baos.size();

        return ((inputLength - compressedLength) / inputLength);
    }
}
