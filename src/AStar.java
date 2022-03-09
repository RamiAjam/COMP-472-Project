import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar {

    private long searchPathCost;
    private int pathCost;
    private ArrayList<String> pathToGoal = new ArrayList<>();
    private final Node goalState;

    private final ArrayList<String> openListHamStr = new ArrayList<>();
    private final Queue<Node> openListHam = new PriorityQueue<>(Node.hammingStarCompare);

    private final ArrayList<String> openListManStr = new ArrayList<>();
    private final Queue<Node> openListMan = new PriorityQueue<>(Node.manhattanStarCompare);

    private final ArrayList<String> openListPerStr = new ArrayList<>();
    private final Queue<Node> openListPer = new PriorityQueue<>(Node.permutationStarCompare);

    private final ArrayList<String> openListNilssonStr = new ArrayList<>();
    private final Queue<Node> openListNilsson = new PriorityQueue<>(Node.inadmissibleStarCompare);


    public AStar(Node root, Node goalState){

        this.goalState = goalState;
        openListHam.add(root);
        openListHamStr.add(root.getStateStringRep());
        openListMan.add(root);
        openListManStr.add(root.getStateStringRep());
        openListPer.add(root);
        openListPerStr.add(root.getStateStringRep());
        openListNilsson.add(root);
        openListNilssonStr.add(root.getStateStringRep());

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

    public boolean solveHamming(){

        searchPathCost = 0;
        pathCost = 0;
        pathToGoal = new ArrayList<>();
        ArrayList<String> closedList = new ArrayList<>();
        Node currentNode;

        do{
            searchPathCost += 1; // the number of nodes checked
            currentNode = openListHam.poll(); // gets the head of the queue

            // finds the state in the string representation list and removes it
            for(int i = 0; i < openListHamStr.size(); i++){
                if(openListHamStr.get(i).equals(currentNode.getStateStringRep())){
                    openListHamStr.remove(i);
                    break;
                }
            }

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
                child.setNodeCost(child.getParent().getNodeCost() + 1);
                child.setFxHam(hammingDistance(child) + child.getNodeCost());
                if(!closedList.contains(child.getStateStringRep())){
                    openListHam.add(child);
                    openListHamStr.add(child.getStateStringRep());
                }
            }

            closedList.add(currentNode.getStateStringRep());

        }while(!openListHam.isEmpty());
        return false;
    }

    public boolean solveManhattan(){

        searchPathCost = 0;
        pathCost = 0;
        pathToGoal = new ArrayList<>();
        ArrayList<String> closedList = new ArrayList<>();
        Node currentNode;

        do{

            searchPathCost += 1; // the number of nodes checked
            currentNode = openListMan.poll(); // gets the head of the queue

            // finds the state in the string representation list and removes it
            for(int i = 0; i < openListManStr.size(); i++){
                if(openListManStr.get(i).equals(currentNode.getStateStringRep())){
                    openListManStr.remove(i);
                    break;
                }
            }

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
                child.setNodeCost(child.getParent().getNodeCost() + 1);
                child.setFxMan(manhattanDistance(child) + child.getNodeCost());
                if(!closedList.contains(child.getStateStringRep())){
                    openListMan.add(child);
                    openListManStr.add(child.getStateStringRep());
                }
            }

            closedList.add(currentNode.getStateStringRep());

        }while(!openListMan.isEmpty());
        return false;
    }

    public boolean solvePermutation(){

        searchPathCost = 0;
        pathCost = 0;
        pathToGoal = new ArrayList<>();
        ArrayList<String> closedList = new ArrayList<>();
        Node currentNode;

        do{

            searchPathCost += 1; // the number of nodes checked
            currentNode = openListPer.poll(); // gets the head of the queue

            // finds the state in the string representation list and removes it
            for(int i = 0; i < openListPerStr.size(); i++){
                if(openListPerStr.get(i).equals(currentNode.getStateStringRep())){
                    openListPerStr.remove(i);
                    break;
                }
            }

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
                child.setNodeCost(child.getParent().getNodeCost() + 1);
                child.setFxPer(permutationInversion(child) + child.getNodeCost());
                if(!closedList.contains(child.getStateStringRep())){
                    openListPer.add(child);
                    openListPerStr.add(child.getStateStringRep());
                }
            }

            closedList.add(currentNode.getStateStringRep());

        }while(!openListPer.isEmpty());
        return false;
    }

    public boolean solveInadmissible(){

        searchPathCost = 0;
        pathCost = 0;
        pathToGoal = new ArrayList<>();
        ArrayList<String> closedList = new ArrayList<>();
        Node currentNode;

        do{

            searchPathCost += 1; // the number of nodes checked
            currentNode = openListNilsson.poll(); // gets the head of the queue

            // finds the state in the string representation list and removes it
            for(int i = 0; i < openListNilssonStr.size(); i++){
                if(openListNilssonStr.get(i).equals(currentNode.getStateStringRep())){
                    openListNilssonStr.remove(i);
                    break;
                }
            }

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
                child.setNodeCost(child.getParent().getNodeCost() + 1);
                child.setFxIndamissible(nilssonHeuristic(child) + child.getNodeCost());
                if(!closedList.contains(child.getStateStringRep())){
                    openListNilsson.add(child);
                    openListNilssonStr.add(child.getStateStringRep());
                }
            }

            closedList.add(currentNode.getStateStringRep());

        }while(!openListNilsson.isEmpty());
        return false;
    }

    // find the hamming distance between the passed state and the goal state
    public int hammingDistance(Node node){

        int counter = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                if(node.getState()[i][j].equals("B"))
                    continue;

                if(!node.getState()[i][j].equals(goalState.getState()[i][j]))
                    counter++;
            }
        }

        return counter;
    }

    // find the manhattan distance between the passed state and the goal state
    public int manhattanDistance(Node node){

        int counter = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){

                if(node.getState()[i][j].equals("B"))
                    continue;

                outerloop:
                for(int k = 0; k < 3; k++){
                    for(int m = 0; m < 3; m++){
                        if (node.getState()[i][j].equals(goalState.getState()[k][m])){
                            counter += Math.abs(i-k) + Math.abs(j-m);
                            break outerloop;
                        }
                    }
                }
            }
        }

        return counter;
    }

    // find the permutation inversion between the passed state and the goal state
    public int permutationInversion(Node node){

        int counter = 0;

        for(int i = 0; i < node.getStateArrRep().size(); i++){

            switch (node.getStateArrRep().get(i)){

                case "1":

                case "B":
                    break;

                case "2":
                    for(int j = i + 1; j < node.getStateArrRep().size(); j++) {
                        if (node.getStateArrRep().get(j).equals("1")) {
                            counter += 1;
                        }
                    }
                    break;

                case "3":
                    for(int j = i + 1; j < node.getStateArrRep().size(); j++) {
                        if (node.getStateArrRep().get(j).equals("1") || node.getStateArrRep().get(j).equals("2")) {
                            counter += 1;
                        }
                    }
                    break;

                case "4":
                    for(int j = i + 1; j < node.getStateArrRep().size(); j++) {
                        if (node.getStateArrRep().get(j).equals("1") || node.getStateArrRep().get(j).equals("2") || node.getStateArrRep().get(j).equals("3")
                                || node.getStateArrRep().get(j).equals("8")) {
                            counter += 1;
                        }
                    }
                    break;

                case "5":
                    for(int j = i + 1; j < node.getStateArrRep().size(); j++) {
                        if (node.getStateArrRep().get(j).equals("1") || node.getStateArrRep().get(j).equals("2") || node.getStateArrRep().get(j).equals("3")
                                || node.getStateArrRep().get(j).equals("4") || node.getStateArrRep().get(j).equals("6") || node.getStateArrRep().get(j).equals("7")
                                || node.getStateArrRep().get(j).equals("8")) {
                            counter += 1;
                        }
                    }
                    break;

                case "6":
                    for(int j = i + 1; j < node.getStateArrRep().size(); j++) {
                        if (node.getStateArrRep().get(j).equals("1") || node.getStateArrRep().get(j).equals("2") || node.getStateArrRep().get(j).equals("3")
                                || node.getStateArrRep().get(j).equals("8") || node.getStateArrRep().get(j).equals("4")
                                || node.getStateArrRep().get(j).equals("7")) {
                            counter += 1;
                        }
                    }
                    break;

                case "7":
                    for(int j = i + 1; j < node.getStateArrRep().size(); j++) {
                        if (node.getStateArrRep().get(j).equals("1") || node.getStateArrRep().get(j).equals("2") || node.getStateArrRep().get(j).equals("3")
                                || node.getStateArrRep().get(j).equals("8") || node.getStateArrRep().get(j).equals("4")) {
                            counter += 1;
                        }
                    }
                    break;

                case "8":
                    for(int j = i + 1; j < node.getStateArrRep().size(); j++) {
                        if (node.getStateArrRep().get(j).equals("1") || node.getStateArrRep().get(j).equals("2") || node.getStateArrRep().get(j).equals("3")) {
                            counter += 1;
                        }
                    }
                    break;
            }
        }
        return counter;
    }

    // find the inadmissible heuristic between the passed state and the goal state
    // This heuristic would take the manhattan distance at first. Then, checks the outer tiles if the next tile is the value that should come after the
    // value of the current tile im at. If so, skip - else add 6. Also, it checks if the middle tile is the blank. if so, skip - else add 3
    public int nilssonHeuristic(Node node){

        int counter = manhattanDistance(node);
        ArrayList<String> temp = new ArrayList<>();

        // changing the array into the nelsson sequence
        for(int i = 0; i < 3; i++){
            temp.add(node.getStateArrRep().get(i));
        }
        temp.add(node.getStateArrRep().get(5));
        for(int i = 8; i > 5; i--){
            temp.add(node.getStateArrRep().get(i));
        }
        temp.add(node.getStateArrRep().get(3));
        temp.add(node.getStateArrRep().get(4));

        if(!temp.get(8).equals("B")){
            counter += 3; // this implies the + 1 multiplied by 3
        }

        for(int i = 0; i < temp.size()-2; i++){
            if(temp.get(i).equals("B")){
                if(i == 0){ // handles the case when its the end of the clockwise loop and 7th index is checking if 1st index is the one that should come next
                    counter += 6;
                }
            }else if(temp.get(i+1).equals("B")){
                counter += 6;
            }else{
                if(((Integer.parseInt(temp.get(i+1))-1) != Integer.parseInt(temp.get(i)))){
                    counter += 6; // this implies the + 2 multiplied by 3
                }
            }
        }

        if(!temp.get(7).equals("B") && !temp.get(0).equals("B")){
            if((Integer.parseInt(temp.get(0))-1) != Integer.parseInt(temp.get(7)))
                counter += 6;
        }

        return counter;

    }


}
