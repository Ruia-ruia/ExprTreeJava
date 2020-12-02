import java.util.Iterator;
import java.util.List;
import java.util.ArrayList; 
import java.util.NoSuchElementException;
import java.util.Scanner; 

public class ETree {

    private Node root;
    private long size; 

    public ETree() {
        root = null;
    }

    private Node findSpot(Integer key) {
        Node currNode = root;
        while (true) {
            if (key.compareTo(currNode.getKey()) == 0) {
                break; 
            }
            // if key is less than currNode go left
            if (key.compareTo(currNode.getKey()) < 0) {
                if (currNode.getLeft() == null) {
                    break; 
                }
                currNode = currNode.getLeft();
                // if key is greater than currNode go right
            } else {
                if (currNode.getRight() == null) {
                    break; 
                }
                currNode = currNode.getRight();
            }
        }
        return currNode;   
    }

    public void put(Integer key, String value) {
        if (key == null || value == null) {
            return; 
        }
        Node newNode = new Node(key, value);
        if (root == null || key.equals(root)) {
            root = newNode; 
            return;
        } else {
            Node currNode = findSpot(key);
            if (key.compareTo(currNode.getKey()) == 0) {
                currNode.setValue(value);
            } else if (key.compareTo(currNode.getKey()) < 0) {
                currNode.setLeft(newNode);
            } else {
                currNode.setRight(newNode);
            }    
        }
        size += 1;      
    }

    public String get(Integer key) {
        Node retNode = this.findSpot(key);
        if (retNode == null || ! retNode.getKey().equals(key)) {
            return null; 
        } else {
            return retNode.getValue();
        }
    }

    public long size() {
        return this.size; 
    }

    public Node getRoot() {
        return this.root;
    }

    public Integer computeTree(Node current) {
        
        if (current != null) {
            // a number is a terminal symbol, so base case is reached
            if (current.getLeft() == null && current.getRight() == null) {
                return Integer.parseInt(current.getValue()); 
            }
        
            // two paths of recursion which 
            // converge to number type nodes
            Integer left = computeTree(current.left);
            Integer right = computeTree(current.right);

            if (current.getValue().equals("+")) {
                return left + right; 
            } else if (current.getValue().equals("-")) {
                return left - right; 
            } else if (current.getValue().equals("*")) {
                return left * right;
            } else {
                return left / right; 
            } 
        }
        // current == null, should never get here 
        return 0; 
    }

    // transform arithmetic to tree 
    public void parseSeq(String s) {
        if (s == null) {
            return; 
        }
        String[] seq = s.split(" ");
        if (seq.length % 2 == 0) {
            System.out.println("Invalid.");
            return; 
        }
        // even indices
        for (int i = 0; i < seq.length; i++) {
            if ((i + 1) % 2 == 0) {
                this.put(i + 1, seq[i]);
            }
        }
        // odd indices
        for (int i = 0; i < seq.length; i++) {
            if ((i + 1) % 2 != 0) {
                this.put(i + 1, seq[i]);
            }
        }
    }

    public void clear() {
        this.root = null; 
    }

    public static void main(String[] args) {
        ETree tree = new ETree();
        Scanner scanin = new Scanner(System.in);
        
        while (true) {
            System.out.print("Enter arithmetic string: ");
            String s = scanin.nextLine(); 
            if (s.equals("q")) {
                // quit 
                break; 
            }
            System.out.println("Evaluating: " + s);
            tree.parseSeq(s);
            Integer result = tree.computeTree(tree.getRoot());
            tree.clear(); 
            System.out.println(result);
        }
    }
}
