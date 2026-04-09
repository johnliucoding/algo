package goodrich.ch11;

/**
 * @author Liu Xiaodong
 * @since 2024/11/18 11:07 PM
 */
public class RedBlackTree<T extends Comparable<? super T>> {

    private static class Node<T> {
        T element; // the data in the node
        Node<T> left; // left child
        Node<T> right; // right child
        int color; // color

        public Node(T element,
                    Node<T> left,
                    Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.color = RedBlackTree.BLACK;
        }

        public Node(T element) {
            this(element, null, null);
        }
    }

    private Node<T> header;
    private Node<T> nullNode;

    private static final int BLACK = 1;    // BLACK must be 1
    private static final int RED   = 0;

    // Used in insert routine and its helpers
    private Node<T> current;
    private Node<T> parent;
    private Node<T> grand;
    private Node<T> great;


    public RedBlackTree() {

        nullNode = new Node<>(null);
        nullNode.left = nullNode.right = nullNode;

        header = new Node<>(null);
        header.left = header.right = nullNode;

    }

    public void remove(T item) {

    }

    /**
     * find the smallest item in the tree
     * @return the smallest item or null if empty
     */
    public T findMin() {
        if(isEmpty()) {
            return null;
        }
        Node<T> itr = header.right;
        while (itr.left != nullNode) {
            itr = itr.left;
        }
        return itr.element;
    }

    /**
     * find the largest item in the tree
     * @return the largest item or null if empty
     */
    public T findMax() {
        if(isEmpty()) {
            return null;
        }
        Node<T> itr = header.right;
        while (itr.right != nullNode) {
            itr = itr.right;
        }
        return itr.element;
    }

    /**
     * find an item in the tree
     * @param x the item to search for
     * @return the matching item or null if not found
     */
    public T find(T x) {
        nullNode.element = x; // this is important
        current = header.right;

        for( ; ; )
        {
            if( x.compareTo( current.element ) < 0 )
                current = current.left;
            else if( x.compareTo( current.element ) > 0 )
                current = current.right;
            else if( current != nullNode )
                return current.element;
            else
                return null;
        }
    }

    /**
     * make the tree logically empty
     */
    public void clear() {
        header.right = nullNode;
    }

    /**
     * test if the tree is logically empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return  header.right == nullNode;
    }

    /**
     * print all items
     */
    public void printTree() {
        printTree(header.right);
    }

    /**
     * internal method to print a subtree in sorted order
     * @param t the node that roots the tree
     */
    private void printTree(Node<T> t) {
        if(t != nullNode) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
    /**
     * Compare item and t.element, using compareTo, with
     * caveat that if t is header, then item is always larger.
     * This routine is called if is possible that t is header.
     * If it is not possible for t to be header, use compareTo directly.
     */
    private int compare(T item, Node<T> t ) {
        if(t == header) {
            return  1;
        } else {
            return item.compareTo(t.element);
        }

    }

    /**
     * insert into the tree
     * @param item the item to insert
     * @throws DuplicateItemException if item is already present
     */
    public void insert(T item) {
        current = parent = grand = header;
        nullNode.element = item;
        while (compare(item, current) != 0) {
            great = grand;
            grand = parent;
            parent = current;
            current = compare(item, current) < 0 ? current.left : current.right;

            // check if two red children; if so, fix
            if(current.left.color == RED && current.right.color == RED) {
                handleReorient(item);
            }
        }

        if(current != nullNode) {
            throw new DuplicateItemException(item.toString());
        }

        current = new Node<T>(item, nullNode, nullNode);


        // attach to parent
        if(compare(item, parent) < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }

        handleReorient(item);

    }
    private void handleReorient(T item) {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;
        if( parent.color == RED )   // Have to rotate
        {
            grand.color = RED;
            if( ( compare( item, grand ) < 0 ) !=
                    ( compare( item, parent ) < 0 ) )
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate( item, great ); // 返回了great的child
            current.color = BLACK;
        }
        header.right.color = BLACK; // Make root black

    }
    private Node<T> rotate(T item, Node<T> parent) {
        if( compare( item, parent ) < 0 )
            return parent.left = compare( item, parent.left ) < 0 ?
                    rotateWithLeftChild( parent.left )  :  // LL
                    rotateWithRightChild( parent.left ) ;  // LR
        else
            //
            return parent.right = compare( item, parent.right ) < 0 ?
                    rotateWithLeftChild( parent.right ) :  // RL
                    rotateWithRightChild( parent.right );  // RR

    }
    /**
     * Rotate binary tree node with left child.
     */
    private static <T> Node<T> rotateWithLeftChild( Node<T> k2 )
    {
        Node<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     */
    private static <T> Node<T> rotateWithRightChild( Node<T> k1 )
    {
        Node<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }
    public static void main( String [ ] args )
    {
        RedBlackTree<Integer> t = new RedBlackTree<Integer>( );
        final int NUMS = 400000;
        final int GAP  =  35461;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        if( t.findMin( ) != 1 || t.findMax( ) != NUMS - 1 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 1; i < NUMS; i++ )
            if( t.find( i ) != i )
                System.out.println( "Find error1!" );
    }
}
