package Huffman;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String inFileName = args[0];
        String outFileName = args[1];
        int stringSize = 0;

        if (args.length > 2) {
            stringSize = Integer.parseInt(args[2]);
        }

        if (stringSize > 0) {
            compress(inFileName, outFileName, stringSize);
        } else {
            decompress(inFileName, outFileName);
        }
    }

    private static void compress(String inFileName, String outFileName, int stringSize) throws Exception {
        float compressionCoefficient;
        InputStream inFile = new FileInputStream(inFileName);
        OutputStream outFile = new FileOutputStream(outFileName);

        TextEvaluator evaluator = new TextEvaluator(inFile, stringSize);
        TreeBuilder treeBuilder = new TreeBuilder(evaluator.evaluate());
        TextEncoder encoder = new TextEncoder(treeBuilder.buildTree(), stringSize);

        inFile.close();
        inFile = new FileInputStream(inFileName);

        compressionCoefficient = encoder.encode(inFile, outFile);

        inFile.close();
        outFile.close();

        System.out.println("Compression successful");
        System.out.println("Compression coefficient = " + compressionCoefficient);
    }

    private static void decompress(String inFileName, String outFileName) throws Exception {
        InputStream inFile = new FileInputStream(inFileName);
        OutputStream outFile = new FileOutputStream(outFileName);

        TextDecoder decoder = new TextDecoder();

        decoder.decode(inFile, outFile);

        inFile.close();
        outFile.close();

        System.out.println("Decompression successful");
    }
}
