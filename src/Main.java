import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

//    private static final Node greaterChallenge = new Node(new String[]{"5","1","4","7","B","6","3","8","2"});
//    private static final Node testing = new Node(new String[]{"3","5","B","2","1","4","8","7","6"});
//    private static final Node Challenge = new Node(new String[]{"2","8","3","1","6","4","7","B","5"});
    private static final Node goalState = new Node(new String[]{"1","2","3","8","B","4","7","6","5"});
    private static final File file = new File("output.txt");

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the initial state one number at a time separated with a space and the blank tile represented as B");
        String[] input = sc.nextLine().split("\\s+");

        System.out.print("The input you inserted is: ");
        for (String x: input) {
            System.out.print(x + " ");
        }
        Node initialState = new Node(input);
        System.out.println("\n");

        try{

            FileWriter fr = new FileWriter(file);

            /********************************* A* Search - Admissible Heuristics **********************************************/

            System.out.println("A* Loading ...");
            AStar aStar = new AStar(initialState, goalState);

            /************ Hamming Distance ******************/
            if(!aStar.solveHamming()){
                fr.write("Failure to find solution using Hamming Distance\n");
                System.out.println("Failure to find solution using Hamming Distance");
                return;
            }
            fr.write("Solution Found using A* Search - Hamming Distance!!\n");
            fr.write("Search Path Cost: " + aStar.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + aStar.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: aStar.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: aStar.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }

            /************ Manhattan Distance ******************/
            fr.write("\n\n");

            if(!aStar.solveManhattan()){
                System.out.println("\n");
                System.out.println("Failure to find solution using Manhattan Distance");
                fr.write("Failure to find solution using Manhattan Distance\n");
                return;
            }

            fr.write("Solution Found using A* Search - Manhattan Distance!!\n");
            fr.write("Search Path Cost: " + aStar.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + aStar.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: aStar.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: aStar.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }

            /************ Permutation Inversion ******************/
            fr.write("\n\n");

            if(!aStar.solvePermutation()){
                System.out.println("\n");
                System.out.println("Failure to find solution using Permutation Inversion");
                fr.write("Failure to find solution using Permutation Inversion\n");
                return;
            }
            fr.write("Solution Found using A* Search - Permutation Inversion!!\n");
            fr.write("Search Path Cost: " + aStar.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + aStar.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: aStar.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: aStar.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }

            /************ Inadmissible Heuristics ******************/
            fr.write("\n\n");

            if(!aStar.solveInadmissible()){
                System.out.println("\n");
                System.out.println("Failure to find solution using the Inadmissible Heuristics");
                fr.write("Failure to find solution the Inadmissible Heuristics\n");
                return;
            }
            fr.write("Solution Found using A* Search - Inadmissible Heuristic!!\n");
            fr.write("Search Path Cost: " + aStar.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + aStar.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: aStar.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: aStar.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }
            System.out.println("Check output.txt file for the A* output");
            /*****************************************************************************************************/

            /********************************* Best First Search - Admissible Heuristics **********************************************/

            System.out.println("\n");
            fr.write("\n\n");
            System.out.println("Best First Search Loading ...");
            Best_First bf = new Best_First(initialState, goalState);

            /************ Hamming Distance ******************/
            if(!bf.solveHamming()){
                System.out.println("Failure to find solution using Hamming Distance");
                fr.write("Failure to find solution using Hamming Distance\n");
                return;
            }

            fr.write("Solution Found using Best First Search - Hamming Distance!!\n");
            fr.write("Search Path Cost: " + bf.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + bf.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: bf.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: bf.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }

            /************ Manhattan Distance ******************/
            fr.write("\n\n");

            if(!bf.solveManhattan()){
                System.out.println("\n");
                System.out.println("Failure to find solution using Manhattan Distance");
                fr.write("Failure to find solution using Manhattan Distance\n");
                return;
            }
            fr.write("Solution Found using Best First Search - Manhattan Distance!!\n");
            fr.write("Search Path Cost: " + bf.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + bf.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: bf.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: bf.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }

            /************ Permutation Inversion ******************/
            fr.write("\n\n");

            if(!bf.solvePermutation()){
                System.out.println("\n");
                System.out.println("Failure to find solution using Permutation Inversion");
                fr.write("Failure to find solution using Permutation Inversion\n");
                return;
            }
            fr.write("Solution Found using Best First Search - Permutation Inversion!!\n");
            fr.write("Search Path Cost: " + bf.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + bf.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: bf.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: bf.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }

            /************ Inadmissible Heuristics ******************/
            fr.write("\n\n");

            if(!bf.solveInadmissible()){
                System.out.println("\n");
                System.out.println("Failure to find solution using the Inadmissible Heuristics");
                fr.write("Failure to find solution\n");
                return;
            }
            fr.write("Solution Found using Best First Search - Inadmissible Heuristics!!\n");
            fr.write("Search Path Cost: " + bf.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + bf.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: bf.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: bf.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }
            System.out.println("Check output.txt file for the Best First Search output");
            /*****************************************************************************************************/

            /********************************* Breadth First Search **********************************************/
            System.out.println("\n");
            fr.write("\n\n");
            System.out.println("BFS Loading ...");
            BFS bfs = new BFS(initialState, goalState);
            if(!bfs.solveBFS()){
                fr.write("Failure to find solution using BFS\n");
                System.out.println("Failure to find solution using BFS");
                return;
            }
            System.out.println("Solution Found using BFS !!");
            fr.write("Solution Found using BFS !!\n");
            fr.write("Search Path Cost: " + bfs.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + bfs.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: bfs.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: bfs.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }
            System.out.println("Check output.txt file for the BFS output");
            /*****************************************************************************************************/

            /********************************* Depth First Search **********************************************/
            System.out.println("\n");
            fr.write("\n\n");
            System.out.println("DFS Loading ...");
            DFS dfs = new DFS(initialState, goalState);
            if(!dfs.solveDFS()){
                fr.write("Failure to find solution using DFS\n");
                System.out.println("Failure to find solution using DFS");
                return;
            }

            System.out.println("Solution Found using DFS !!");
            fr.write("Solution Found using DFS !!\n");
            fr.write("Search Path Cost: " + dfs.getSearchPathCost() + "\n");
            fr.write("Path Cost: " + dfs.getPathCost() + "\n");
//            fr.write("Path to Goal is ( in terms of moves ): \n");
//            for (String x: dfs.getPathToGoal()) {
//                fr.write(x + ", ");
//            }

            fr.write("\n");
            fr.write("Path to Goal is ( in terms of Nodes ): \n");
            for (Node x: dfs.getPathToGoalNode()) {
                fr.write("[");
                for (String s : x.getStateArrRep()) {
                    fr.write(s + " ");
                }
                fr.write("], ");
            }
            System.out.println("Check output.txt file for the DFS output");
            /*****************************************************************************************************/

            fr.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
