import java.util.*;

public class Main {

    private static final Node initialState = new Node(new String[]{"3","5","B","2","1","4","8","7","6"});
    private static final Node test = new Node(new String[]{"1","B","8","4","2","5","7","3","6"});
    private static final Node temp = new Node(new String[]{"1","2","3","7","B","6","4","8","5"});
    private static final Node temp2 = new Node(new String[]{"1","2","3","8","B","6","4","7","5"});
    private static final Node BFS_Example = new Node(new String[]{"5","1","4","7","B","6","3","8","2"});
    private static final Node DFS_Example = new Node(new String[]{"3","5","B","2","1","4","8","7","6"});
    private static final Node goalState = new Node(new String[]{"1","2","3","8","B","4","7","6","5"});

    private static final Queue<Node> openList = new PriorityQueue<>(Node.permutationCompare);

    public static void main(String[] args) {
//
//       Best_First bf = new Best_First(DFS_Example, goalState);
//        initialState.setHam(bf.permutationInversion(initialState));
//        test.setPer(bf.permutationInversion(test));
//        temp.setPer(bf.permutationInversion(temp));
//        temp2.setPer(bf.permutationInversion(temp2));
//
//        openList.add(temp);
//        openList.add(initialState);
//
////        int x = openList.size();
////        for(int i = 0; i < x; i++){
////            if(openList.get(i).getHam() > temp.getHam() )
////                openList.
////        }
//
//        openList.add(test);
//        openList.add(temp2);
//        //openList.add(initialState);
//
//        //System.out.println(openList.size());
//
//        int x = openList.size();
//        for(int i = 0; i < x; i++){
//            System.out.println(openList.poll().getPer());
//            //printMatrix(openList.poll());
//        }

        /********************************* Breadth First Search **********************************************/
//        BFS bfs = new BFS(BFS_Example, goalState);
//        if(!bfs.solveBFS()){
//            System.out.println("Failure to find solution");
//            return;
//        }
//        System.out.println("Solution Found using BFS !!");
//
//        System.out.println("Search Path Cost: " + bfs.getSearchPathCost());
//        System.out.println("Path Cost: " + bfs.getPathCost());
//
//        System.out.println("Path to Goal is: ");
//        for (String x: bfs.getPathToGoal()) {
//            System.out.print(x + ", ");
//        }
        /*****************************************************************************************************/

        /********************************* Depth First Search **********************************************/
//        DFS dfs = new DFS(DFS_Example, goalState);
//        if(!dfs.solveDFS()){
//            System.out.println("Failure to find solution");
//            return;
//        }
//        System.out.println("Solution Found using DFS !!");
//
//        System.out.println("Search Path Cost: " + dfs.getSearchPathCost());
//        System.out.println("Path Cost: " + dfs.getPathCost());
//
//        System.out.println("Path to Goal is: ");
//        for (String x: dfs.getPathToGoal()) {
//            System.out.print(x + ", ");
//        }
        /*****************************************************************************************************/

        /********************************* Best First Search - Admissible Heuristics **********************************************/

        Best_First bf = new Best_First(BFS_Example, goalState);

//        /************ Hamming Distance ******************/
//        if(!bf.solveHamming()){
//            System.out.println("Failure to find solution");
//            return;
//        }
//        System.out.println("Solution Found using Best First Search - Hamming Distance!!");
//
//        System.out.println("Search Path Cost: " + bf.getSearchPathCost());
//        System.out.println("Path Cost: " + bf.getPathCost());
//
//        System.out.println("Path to Goal is: ");
//        for (String x: bf.getPathToGoal()) {
//            System.out.print(x + ", ");
//        }
//
//        /************ Manhattan Distance ******************/
//        System.out.println("\n");
//
//        if(!bf.solveManhattan()){
//            System.out.println("Failure to find solution");
//            return;
//        }
//        System.out.println("Solution Found using Best First Search - Manhattan Distance!!");
//
//        System.out.println("Search Path Cost: " + bf.getSearchPathCost());
//        System.out.println("Path Cost: " + bf.getPathCost());
//
//        System.out.println("Path to Goal is: ");
//        for (String x: bf.getPathToGoal()) {
//            System.out.print(x + ", ");
//        }

        /************ Permutation Inversion ******************/
        System.out.println("\n");

        if(!bf.solvePermutation()){
            System.out.println("Failure to find solution");
            return;
        }
        System.out.println("Solution Found using Best First Search - Permutation Inversion!!");

        System.out.println("Search Path Cost: " + bf.getSearchPathCost());
        System.out.println("Path Cost: " + bf.getPathCost());

        System.out.println("Path to Goal is: ");
        for (String x: bf.getPathToGoal()) {
            System.out.print(x + ", ");
        }

//        /************ Inadmissible Heuristics ******************/
//        System.out.println("\n");
//
//        if(!bf.solveInadmissible()){
//            System.out.println("Failure to find solution");
//            return;
//        }
//        System.out.println("Solution Found using Best First Search - Inadmissible Search!!");
//
//        System.out.println("Search Path Cost: " + bf.getSearchPathCost());
//        System.out.println("Path Cost: " + bf.getPathCost());
//
//        System.out.println("Path to Goal is: ");
//        for (String x: bf.getPathToGoal()) {
//            System.out.print(x + ", ");
//        }
        /*****************************************************************************************************/

//        System.out.println(Arrays.deepToString(temp.getParent()));
//        printMatrix(temp);
//
//        ArrayList<Node> children = temp.generateChildren();
//
//        for (Node child : children) {
//            printMatrix(child);
//            System.out.println("Parent is: " );
//            printMatrix(child.getParent());
//        }
//
//        ArrayList<Node> testing = children.get(0).generateChildren();
//
//        for (Node child : testing) {
//            printMatrix(child);
//            System.out.println("CHILD-Parent is: " + Arrays.deepToString(child.getParent()));
//        }

//        Best_First bf = new Best_First(initialState, goalState);
//        System.out.println("hamming: " + bf.hammingDistance(initialState));
//        System.out.println("manhattan: " + bf.manhattanDistance(initialState));
//        System.out.println("permutation: " + bf.permutationInversion(temp));

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
