/**
 * Created by sravanm on 19-12-2016.
 */
// Java program for insertion, Deletion, pre order, post order, in order, top view in AVL Tree
class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

class AVLTree {

    Node root;

    // A utility function to get height of the tree
    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    Node delete(Node node, int key) {

        if (node.key == key) {
            if(node.right != null) {
                Node right = node.right;
                if(node.left != null) {
                    Node xleft = right.left;
                    right.left = node.left;
                    right.left.right = xleft;
                }
                return right;
            }

            else if(node.left != null) {
                return node.left;
            } else {
                return null;
            }
        }

        if (key < node.key)
            node.left = delete(node.left, key);
        else if (key > node.key)
            node.right = delete(node.right, key);
        else // Duplicate keys not allowed
            return node;

        node.height =  max(height(node.left), height(node.right)) - 1;

        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key > node.left.key)
            return rightRotate(node);

        if (balance < -1 && key < node.right.key)
            return leftRotate(node);

        if (balance > 1 && key < node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key > node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node insert(Node node, int key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
            height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);

        }
    }

    public static boolean isBalancedTree(Node node) {
        boolean isBalanced = true;
        if(node.left == null && node.right == null) {
            return true;
        }

        if(node.left!= null && node.key < node.left.key ) {
            return false;
        }

        if(node.right!= null && node.key > node.right.key ) {
            return false;
        }

        if(node.right != null) {
            return isBalancedTree(node.right);
        }

        if(node.left != null) {
            return isBalancedTree(node.left);
        }

        return true;
    }

    public static void top_view(Node root)
    {
        printLeft(root.left);
        System.out.print(root.key+" ");
        printRight(root.right);
    }

    public static void printLeft(Node left) {
        if(left.left != null) {
            printLeft(left.left);
        }
        System.out.print(left.key+" ");
    }

    public static void printRight(Node right) {
        System.out.print(right.key+" ");
        if(right.right != null) {
            printLeft(right.right);
        }
    }


    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, 45);


        tree.root = tree.delete(tree.root, 10);
        /* The constructed AVL Tree would be
               30
            /     \
          20       45
         /  \     /  \
        10  25  40    50
        */
        System.out.println("Pre Order traversal" +
            " of constructed tree is : ");
        tree.preOrder(tree.root);
        System.out.println("\nPost Order traversal" +
            " of constructed tree is : ");
        tree.postOrder(tree.root);
        System.out.println("\nIn Order traversal" +
            " of constructed tree is : ");
        tree.inOrder(tree.root);

        boolean isBalancedTree = isBalancedTree(tree.root);
        System.out.println(isBalancedTree);

        System.out.println("top view");
        top_view(tree.root);

    }
}

