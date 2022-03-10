import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private long searchPathCost = 0; // number of nodes that has been explored during the search
    private int pathCost = 0; // the cost from the start node to the goal node
    private final ArrayList<String> pathToGoal = new ArrayList<>(); // list of strings representing the moves that needs to be done to reach the goal state
    private final ArrayList<Node> pathToGoalNode = new ArrayList<>(); // list of nodes representing successor states that needs to be followed to reach the goal state
    private final Node goalState;
    private final ArrayList<String> closedList = new ArrayList<>(); // holds all the explored nodes
    private final Queue<Node> openList = new LinkedList<>(); // holds all the generated but not yet explored nodes
    private final Queue<String> openListStr = new LinkedList<>(); // same as openlist but holds strings ( used for checking if node is in the open list or not )

    // constructor
    public BFS(Node root, Node goalState){

        this.goalState = goalState;
        openList.add(root);
        openListStr.add(root.getStateStringRep());
        pathToGoalNode.add(root);

    }

    public ArrayList<Node> getPathToGoalNode() {
        return pathToGoalNode;
    }

    public int getPathCost() {
        return pathCost;
    }

    public ArrayList<String> getPathToGoal() {
        return pathToGoal;
    }

    public long getSearchPathCost(){
        return searchPathCost;
    }

    public boolean solveBFS(){

        Node currentNode;

        do{

            searchPathCost += 1; // the number of nodes checked
            currentNode = openList.poll(); // gets the head of the queue
            openListStr.poll();


            //checks if the root node is the goal state
            if(currentNode.compareNodes(goalState)){

                ArrayList<String> temp = new ArrayList<>();
                ArrayList<Node> temp2 = new ArrayList<>();

                // backtrack while adding the moves until you reach the parent node
                while(currentNode.getParent() != null){
                    temp.add(currentNode.getParentToChildMove());
                    temp2.add(currentNode);
                    currentNode = currentNode.getParent();
                }

                // Append the elements in reverse order
                for(int i = temp.size() - 1; i >= 0; i--) {
                    pathToGoal.add(temp.get(i));
                    pathToGoalNode.add(temp2.get(i));
                }

                pathCost = pathToGoal.size();

                return true;
            }

            // checks if the child is not in the closed list && not in the open list then adds it to the END of the list
            for(Node child: currentNode.generateChildren()){
                if(!closedList.contains(child.getStateStringRep()) && !openListStr.contains(child.getStateStringRep())){
                    openList.add(child);
                    openListStr.add(child.getStateStringRep());
                }
            }

            closedList.add(currentNode.getStateStringRep());

        }while(!openList.isEmpty());
        return false;
    }

}
