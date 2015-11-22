package Huffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TextEvaluator {
    private Reader reader;
    private char[] buffer;
    private List<Symbol> symbols;

    TextEvaluator(InputStream text, int stringSize) {
        reader = new InputStreamReader(text);
        buffer = new char[stringSize];
        symbols = new LinkedList<>();
    }

    public List<Symbol> evaluate() throws IOException {
        StringBuilder builder;
        int read;

        while (0 < (read = reader.read(buffer))) {
            builder  = new StringBuilder();
            builder.append(buffer, 0, read);

            insertSymbol(builder.toString());
        }

        symbols.sort((o1, o2) -> o1.frequency - o2.frequency);

        return symbols;
    }

    private void insertSymbol(String string) {
        int index = 0;
        int comparison;

        for (Symbol s : symbols) {
            comparison = s.string.compareTo(string);

            if (0 == comparison) {
                s.frequency++;
                return;
            } else if (0 < comparison) {
                symbols.add(index, new Symbol(string, 1));
                return;
            }

            index++;
        }

        symbols.add(new Symbol(string, 1));
    }
}
