public class Node {

    Node left;
    Node right;
    Integer key;
    String value; 

    public Node (Integer key, String value) {
        this.key = key;
        this.value = value;  
    }

    public Node getLeft() {
        return this.left;
    }

    public Integer getKey() {
        return this.key;
    }

    public void setKey(Integer key) {
        this.key = key; 
    }

    public String getValue() {
        return this.value; 
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getRight() {
        return this.right; 
    }

    public int getSize() {
        int size = 0;
        if (right != null || left != null) {
            size += 1;
            if (left != null && right != null) {
                size += 1;
            }
        }
        return size; 
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right; 
    }
}  
