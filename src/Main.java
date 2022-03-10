import java.util.*;

public class Main {

    private static final Node initialState = new Node(new String[]{"5","1","4","7","B","6","3","8","2"});
    private static final Node initialState2 = new Node(new String[]{"3","5","B","2","1","4","8","7","6"});
    private static final Node goalState = new Node(new String[]{"1","2","3","8","B","4","7","6","5"});

    private static final Queue<Node> openList = new PriorityQueue<>(Node.permutationCompare);

    public static void main(String[] args) {

        /********************************* Breadth First Search **********************************************/
//        BFS bfs = new BFS(initialState, goalState);
//        if(!bfs.solveBFS()){
//            System.out.println("Failure to find solution");
//            return;
//        }
//        System.out.println("Solution Found using BFS !!");
//
//        System.out.println("Search Path Cost: " + bfs.getSearchPathCost());
//        System.out.println("Path Cost: " + bfs.getPathCost());
//
//        System.out.println("Path to Goal is ( in terms of moves ): ");
//        for (String x: bfs.getPathToGoal()) {
//            System.out.print(x + ", ");
//        }
//        System.out.println();
//        System.out.println("Path to Goal is ( in terms of nodes ): ");
//        for (Node x: bfs.getPathToGoalNode()) {
//            printMatrix(x);
//        }
        /*****************************************************************************************************/

        /********************************* Depth First Search **********************************************/
//        System.out.println("\n");
//        DFS dfs = new DFS(initialState, goalState);
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
//
//        System.out.println();
//        System.out.println("Path to Goal is ( in terms of nodes ): ");
//        for (Node x: bfs.getPathToGoalNode()) {
//            printMatrix(x);
//        }
        /*****************************************************************************************************/

        /********************************* Best First Search - Admissible Heuristics **********************************************/

//        System.out.println("\n");
//        Best_First bf = new Best_First(initialState2, goalState);
//
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
////        System.out.println();
////        System.out.println("Path to Goal is ( in terms of nodes ): ");
////        for (Node x: bf.getPathToGoalNode()) {
////            printMatrix(x);
////        }
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
//
////        System.out.println();
////        System.out.println("Path to Goal is ( in terms of nodes ): ");
////        for (Node x: bf.getPathToGoalNode()) {
////            printMatrix(x);
////        }
//
//        /************ Permutation Inversion ******************/
//        System.out.println("\n");
//
//        if(!bf.solvePermutation()){
//            System.out.println("Failure to find solution");
//            return;
//        }
//        System.out.println("Solution Found using Best First Search - Permutation Inversion!!");
//
//        System.out.println("Search Path Cost: " + bf.getSearchPathCost());
//        System.out.println("Path Cost: " + bf.getPathCost());
//
//        System.out.println("Path to Goal is: ");
//        for (String x: bf.getPathToGoal()) {
//            System.out.print(x + ", ");
//        }
//
////        System.out.println();
////        System.out.println("Path to Goal is ( in terms of nodes ): ");
////        for (Node x: bf.getPathToGoalNode()) {
////            printMatrix(x);
////        }
//
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
//
////        System.out.println();
////        System.out.println("Path to Goal is ( in terms of nodes ): ");
////        for (Node x: bf.getPathToGoalNode()) {
////            printMatrix(x);
////        }
        /*****************************************************************************************************/

        /********************************* A* Search - Admissible Heuristics **********************************************/

        System.out.println("\n");
        AStar aStar = new AStar(initialState2, goalState);

        /************ Hamming Distance ******************/
        if(!aStar.solveHamming()){
            System.out.println("Failure to find solution");
            return;
        }
        System.out.println("Solution Found using A* Search - Hamming Distance!!");

        System.out.println("Search Path Cost: " + aStar.getSearchPathCost());
        System.out.println("Path Cost: " + aStar.getPathCost());

        System.out.println("Path to Goal is: ");
        for (String x: aStar.getPathToGoal()) {
            System.out.print(x + ", ");
        }

//        System.out.println();
//        System.out.println("Path to Goal is ( in terms of nodes ): ");
//        for (Node x: aStar.getPathToGoalNode()) {
//            printMatrix(x);
//        }

        /************ Manhattan Distance ******************/
        System.out.println("\n");

        if(!aStar.solveManhattan()){
            System.out.println("Failure to find solution");
            return;
        }
        System.out.println("Solution Found using A* Search - Manhattan Distance!!");

        System.out.println("Search Path Cost: " + aStar.getSearchPathCost());
        System.out.println("Path Cost: " + aStar.getPathCost());

        System.out.println("Path to Goal is: ");
        for (String x: aStar.getPathToGoal()) {
            System.out.print(x + ", ");
        }

//        System.out.println();
//        System.out.println("Path to Goal is ( in terms of nodes ): ");
//        for (Node x: aStar.getPathToGoalNode()) {
//            printMatrix(x);
//        }

        /************ Permutation Inversion ******************/
        System.out.println("\n");

        if(!aStar.solvePermutation()){
            System.out.println("Failure to find solution");
            return;
        }
        System.out.println("Solution Found using A* Search - Permutation Inversion!!");

        System.out.println("Search Path Cost: " + aStar.getSearchPathCost());
        System.out.println("Path Cost: " + aStar.getPathCost());

        System.out.println("Path to Goal is: ");
        for (String x: aStar.getPathToGoal()) {
            System.out.print(x + ", ");
        }

//        System.out.println();
//        System.out.println("Path to Goal is ( in terms of nodes ): ");
//        for (Node x: aStar.getPathToGoalNode()) {
//            printMatrix(x);
//        }

        /************ Inadmissible Heuristics ******************/
        System.out.println("\n");

        if(!aStar.solveInadmissible()){
            System.out.println("Failure to find solution");
            return;
        }
        System.out.println("Solution Found using A* Search - Inadmissible Search!!");

        System.out.println("Search Path Cost: " + aStar.getSearchPathCost());
        System.out.println("Path Cost: " + aStar.getPathCost());

        System.out.println("Path to Goal is: ");
        for (String x: aStar.getPathToGoal()) {
            System.out.print(x + ", ");
        }

//        System.out.println();
//        System.out.println("Path to Goal is ( in terms of nodes ): ");
//        for (Node x: aStar.getPathToGoalNode()) {
//            printMatrix(x);
//        }
        /*****************************************************************************************************/

    }

    // prints out the 3x3 matrix
    public static void printMatrix(Node n) {
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
