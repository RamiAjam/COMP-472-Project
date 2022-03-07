import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private long searchPathCost = 0;
    private int pathCost = 0;
    private final ArrayList<String> pathToGoal = new ArrayList<>();
    private final Node goalState;
    private final Queue<Node> openList = new LinkedList<>();
    private final ArrayList<String> closedList = new ArrayList<>();
    private final Queue<String> openListStr = new LinkedList<>();

    public BFS(Node root, Node goalState){

        this.goalState = goalState;
        openList.add(root);
        openListStr.add(root.getStateStringRep());

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

                while(currentNode.getParent() != null){
                    temp.add(currentNode.getParentToChildMove());
                    currentNode = currentNode.getParent();
                }

                // Append the elements in reverse order
                for(int i = temp.size() - 1; i >= 0; i--) {
                    pathToGoal.add(temp.get(i));
                }

                pathCost = pathToGoal.size();

                return true;
            }

            // checks if the child is in the closed list then adds it to the BEGINNING of the list
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
