package Assignment5;

public class Node {
    int value;
    Node left = null;
    Node right = null;

    public Node(){}

    public Node(int value){
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
