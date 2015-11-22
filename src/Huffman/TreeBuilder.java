package Huffman;

import java.util.LinkedList;
import java.util.List;

import Huffman.HuffmanTree.Node;

public class TreeBuilder {
    private HuffmanTree tree;
    private List<Node> nodes;

    public TreeBuilder(List<Symbol> symbols) {
        nodes = getNodeList(symbols);
        tree = new HuffmanTree(new LinkedList<>(nodes));
    }

    public HuffmanTree buildTree() {
        Node parent;

        while (nodes.size() > 1) {
            parent = new Node(new Symbol(null, 0));

            parent.setLeftChild(nodes.remove(0));
            parent.setRightChild(nodes.remove(0));

            insertNode(parent);
        }

        tree.setRoot(nodes.remove(0));

        return tree;
    }

    private List<Node> getNodeList(List<Symbol> symbols) {
        LinkedList<Node> nodes = new LinkedList<>();

        for (Symbol symbol : symbols) {
            nodes.addLast(new Node(symbol));
        }

        return nodes;
    }

    private void insertNode(Node node) {
        int index = 0;

        for (Node n : nodes) {
            if (n.getSymbol().frequency >= node.getSymbol().frequency) {
                nodes.add(index, node);
                return;
            }

            index++;
        }

        nodes.add(node);
    }
}
