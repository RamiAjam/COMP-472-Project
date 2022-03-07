import java.util.*;

public class Best_First{

    private long searchPathCost;
    private int pathCost;
    private ArrayList<String> pathToGoal = new ArrayList<>();
    private final Node goalState;
    private final Queue<String> openListHamStr = new LinkedList<>();
    private final Queue<Node> openListHam = new PriorityQueue<>(Node.hammingCompare);
    private final Queue<String> openListManStr = new LinkedList<>();
    private final Queue<Node> openListMan = new PriorityQueue<>(Node.manhattanCompare);
    private final Queue<String> openListPerStr = new LinkedList<>();
    private final Queue<Node> openListPer = new PriorityQueue<>(Node.permutationCompare);
    private final Queue<String> openListInadmissibleStr = new LinkedList<>();
    private final Queue<Node> openListInadmissible = new PriorityQueue<>(Node.inadmissibleCompare);



    public Best_First(Node root, Node goalState){

        this.goalState = goalState;
        openListHam.add(root);
        openListHamStr.add(root.getStateStringRep());
        openListMan.add(root);
        openListManStr.add(root.getStateStringRep());
        openListPer.add(root);
        openListPerStr.add(root.getStateStringRep());
        openListInadmissible.add(root);
        openListInadmissibleStr.add(root.getStateStringRep());

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
            openListHamStr.poll();

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
                child.setHam(hammingDistance(child));
                if(!closedList.contains(child.getStateStringRep()) && !openListHamStr.contains(child.getStateStringRep())){
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
            openListManStr.poll();

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
                child.setMan(manhattanDistance(child));
                if(!closedList.contains(child.getStateStringRep()) && !openListManStr.contains(child.getStateStringRep())){
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
            openListPerStr.poll();

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
                child.setPer(permutationInversion(child));
                if(!closedList.contains(child.getStateStringRep()) && !openListPerStr.contains(child.getStateStringRep())){
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
            currentNode = openListInadmissible.poll(); // gets the head of the queue
            openListInadmissibleStr.poll();

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
                child.setInadmissible(inadmissibleHeuristic(child));
                if(!closedList.contains(child.getStateStringRep()) && !openListInadmissibleStr.contains(child.getStateStringRep())){
                    openListInadmissible.add(child);
                    openListInadmissibleStr.add(child.getStateStringRep());
                }
            }

            closedList.add(currentNode.getStateStringRep());

        }while(!openListInadmissible.isEmpty());
        return false;
    }

    // find the hamming distance between the passed state and the goal state
    public int hammingDistance(Node node){

        int counter = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
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
            if(node.getStateArrRep().get(i).equals("B"))
                continue;
            for(int j = i+1; j < node.getStateArrRep().size(); j++){
                if(node.getStateArrRep().get(j).equals("B"))
                    continue;

                if(Integer.parseInt(node.getStateArrRep().get(j)) < Integer.parseInt(node.getStateArrRep().get(i)))
                    counter++;
            }
        }

        return counter;

    }

    // find the inadmissible heuristic between the passed state and the goal state
    public int inadmissibleHeuristic(Node node){

        int counter = 0;

        for(int i = 0; i < node.getStateArrRep().size(); i++){
            if(node.getStateArrRep().get(i).equals("B"))
                continue;
            for(int j = i+1; j < node.getStateArrRep().size(); j++){
                if(node.getStateArrRep().get(j).equals("B"))
                    continue;

                if(Integer.parseInt(node.getStateArrRep().get(j)) < Integer.parseInt(node.getStateArrRep().get(i)))
                    counter++;
            }
        }

        return counter;

    }

}
