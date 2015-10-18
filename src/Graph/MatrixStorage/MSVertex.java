package Graph.MatrixStorage;

import Graph.Vertex;

class MSVertex<V> extends Vertex<V> {
    private int id;

    MSVertex(int id, V data) {
        super(data);
        this.id = id;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }
}
