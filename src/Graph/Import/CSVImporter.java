package Graph.Import;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import Graph.Vertex;
import Graph.Graph;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@SuppressWarnings("unchecked")
public class CSVImporter {
    public static void importGraph(Graph<Integer> graph, String path) throws IOException {
        HashMap<Integer, Vertex> vertices = new HashMap<>();
        CSVParser parser = CSVParser.parse(new File(path), Charset.defaultCharset(), CSVFormat.newFormat(';'));

        for (CSVRecord record : parser) {
            int value1 = Integer.parseInt(record.get(0).trim());
            int value2 = Integer.parseInt(record.get(1).trim());
            int weight = Integer.parseInt(record.get(2).trim());

            if (!vertices.containsKey(value1)) {
                vertices.put(value1, graph.addVertex(value1));
            }

            if (!vertices.containsKey(value2)) {
                vertices.put(value2, graph.addVertex(value2));
            }

            graph.addEdge(vertices.get(value1), vertices.get(value2), weight);
        }
    }
}
