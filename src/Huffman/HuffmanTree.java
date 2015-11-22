package Huffman;

import java.io.Serializable;
import java.util.List;

public class HuffmanTree implements Serializable {
    private Node root;
    private List<Node> leaves;

    public HuffmanTree(List<Node> leaves) {
        this.leaves = leaves;
    }

    public void setRoot(Node node) {
        root = node;
    }

    public Node getRoot() {
        return root;
    }

    public List<Node> getLeaves() {
        return leaves;
    }

    public String getStringCode(String string) throws Exception {
        for (Node node : leaves) {
            if (node.symbol.string.equals(string)) {
                StringBuilder builder = new StringBuilder();
                Node current = node;
                Node parent = null;

                while (null != current.parent) {
                    parent = current.parent;

                    if (current.equals(parent.leftChild)) {
                        builder.insert(0, '0');
                    } else {
                        builder.insert(0, '1');
                    }

                    current = parent;
                }

                return builder.toString();
            }
        }

        throw new Exception("String not found!");
    }

    public static class Node implements Serializable {
        private Symbol symbol;

        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node(Symbol symbol) {
            this.symbol = symbol;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        public boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }

        private void calculateFrequency() {
            int frequency = 0;

            if (null != leftChild) {
                frequency += leftChild.symbol.frequency;
            }

            if (null != rightChild) {
                frequency += rightChild.symbol.frequency;
            }

            this.symbol.frequency = frequency;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node node) {
            leftChild = node;
            node.parent = this;
            calculateFrequency();
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node node) {
            rightChild = node;
            node.parent = this;
            calculateFrequency();
        }
    }
}
