package Huffman;

import java.io.*;
import java.nio.charset.StandardCharsets;

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

        while (0 < (read = reader.read(buffer))) {
            inputLength += read;
            builder  = new StringBuilder();
            builder.append(buffer, 0, read);

            code = tree.getStringCode(builder.toString());
            compressedLength += code.length();

            bufferedOutput.write(code.getBytes(StandardCharsets.UTF_8));
        }

        objectOutput.flush();
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(tree);
//        oos.close();

        compressedLength = compressedLength/8;// + baos.size();

        return ((inputLength - compressedLength) / inputLength);
    }
}
