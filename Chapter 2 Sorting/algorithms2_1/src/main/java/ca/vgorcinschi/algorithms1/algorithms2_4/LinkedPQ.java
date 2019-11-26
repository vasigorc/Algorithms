package ca.vgorcinschi.algorithms1.algorithms2_4;

/**
 * @param <Key>
 * @author Pavel from Tver State University
 */
public class LinkedPQ<Key extends Comparable<Key>> {
    private class Node {
        int N;
        Key data;
        Node parent, left, right;

        public Node(Key data, int N) {
            this.data = data;
            this.N = N;
        }
    }

    // fields
    private Node root;
    private Node lastInserted;

    //helper methods
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    private void swim(Node x) {
        if (x == null) return;
        if (x.parent == null) return; // we're at root
        int cmp = x.data.compareTo(x.parent.data);
        if (cmp > 0) {
            swapNodeData(x, x.parent);
            swim(x.parent);
        }
    }

    private void sink(Node nextNode) {
        if (nextNode == null) return;
        Node swapNode;
        if (nextNode.left == null && nextNode.right == null) {
            return;
        } else if (nextNode.left == null) {
            swapNode = nextNode.right;
            int cmp = nextNode.data.compareTo(swapNode.data);
            if (cmp < 0)
                swapNodeData(swapNode, nextNode);
        } else if (nextNode.right == null) {
            swapNode = nextNode.left;
            int cmp = nextNode.data.compareTo(swapNode.data);
            if (cmp < 0)
                swapNodeData(swapNode, nextNode);
        } else {
            int cmp = nextNode.left.data.compareTo(nextNode.right.data);
            if (cmp >= 0) {
                swapNode = nextNode.left;
            } else {
                swapNode = nextNode.right;
            }
            int cmpParChild = nextNode.data.compareTo(swapNode.data);
            if (cmpParChild < 0) {
                swapNodeData(swapNode, nextNode);
                sink(swapNode);
            }
        }
    }

    private void swapNodeData(Node x, Node y) {
        Key temp = x.data;
        x.data = y.data;
        y.data = temp;
    }

    private Node insert(Node x, Key data) {
        if (x == null) {
            lastInserted = new Node(data, 1);
            return lastInserted;
        }
        // compare left and right sizes see where to go
        int leftSize = size(x.left);
        int rightSize = size(x.right);

        if (leftSize <= rightSize) {
            // go to left
            Node inserted = insert(x.left, data);
            x.left = inserted;
            inserted.parent = x;
        } else {
            // go to right
            Node inserted = insert(x.right, data);
            x.right = inserted;
            inserted.parent = x;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node resetLastInserted(Node x) {
        if (x == null) return null;
        if (x.left == null && x.right == null) return x;
        if (size(x.right) < size(x.left)) return resetLastInserted(x.left);
        else return resetLastInserted(x.right);
    }

    // public methods
    public void insert(Key data) {
        root = insert(root, data);
        swim(lastInserted);
    }

    public Key max() {
        if (root == null) return null;
        return root.data;
    }

    public Key delMax() {
        if (size() == 1) {
            Key ret = root.data;
            root = null;
            return ret;
        }
        swapNodeData(root, lastInserted);
        Node lastInsParent = lastInserted.parent;
        Key lastInsData = lastInserted.data;
        if (lastInserted == lastInsParent.left) {
            lastInsParent.left = null;
        } else {
            lastInsParent.right = null;
        }

        lastInserted = resetLastInserted(root);

        sink(root);

        return lastInsData;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}