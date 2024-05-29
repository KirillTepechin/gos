package tree;

public class BinarySearchTree {
    Node root = null;

    public void add(int data){
        Node newNode = new Node(data);

        if(root==null){
            root = newNode;
        }
        else{
            Node current = root;
            Node parentNode;
            while (true){
                parentNode = current;
                if(newNode.key <= current.key){
                    current = current.left;
                    if (current == null){ // если был достигнут конец цепочки,
                        parentNode.left = newNode;
                        return;
                    }
                }
                else {
                    current = current.right;
                    if (current == null){ // если был достигнут конец цепочки,
                        parentNode.right = newNode;
                        return;
                    }
                }
            }
        }
    }
    void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    void printTree(Node node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node.key);
            printTree(node.right);
        }
    }
}
