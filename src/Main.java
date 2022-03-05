import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static final Node initialState = new Node(new String[]{"1","2","3","B","8","4","7","6","5"});
    private static final Node temp = new Node(new String[]{"2","3","1","8","4","7","6","5","B"});
    private static final Node goalState = new Node(new String[]{"1","2","3","8","B","4","7","6","5"});

    public static void main(String[] args) {

        printMatrix(temp);
        System.out.println(Arrays.deepToString(temp.getParent()));

        ArrayList<Node> children = temp.generateChildren();

        for (Node child : children) {
            printMatrix(child);
            System.out.println("Parent is: " + Arrays.deepToString(child.getParent()));
        }

        ArrayList<Node> testing = children.get(0).generateChildren();

        for (Node child : testing) {
            printMatrix(child);
            System.out.println("CHILD-Parent is: " + Arrays.deepToString(child.getParent()));
        }
    }

    // prints out the 3x3 matrix
    public static void printMatrix(Node n)
    {
        String[][] a = n.getState();
        for( int i = 0; i < 3; i++)
        {
            for ( int j = 0 ; j < 3 ;j++)
            {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
