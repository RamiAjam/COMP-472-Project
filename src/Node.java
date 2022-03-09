import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Node implements Comparable<Node>{

    private String[][] state = new String[3][3]; // the state of the puzzle represented as a 3 by 3 matrix
    private final ArrayList<Node> children = new ArrayList<>(); // the generated children of this node
    private Node parent; // the parent of this node
    private String blankPos; // gets the position of the blank space
    private String parentToChildMove = null; // represents the move done to go from parent to child ( used for path tracking )
    private String stateStringRep = ""; // represents the state as a single string ( used to be added to the closed list )
    private final ArrayList<String> stateArrRep = new ArrayList<>(); // represents the state as a single string array ( used in permutation inversion )
    private int ham, man , per , inadmissible , fxMan , fxHam, fxPer, fxIndamissible;
    private int nodeCost; // this represents the cost needed to get this node ( parent's cost + 1 )

    public Node(String[][] stateMatrix){
        state = stateMatrix;
        parent = null;
        nodeCost = 0;

        for(int i = 0; i < stateMatrix.length; i++){
            for(int j = 0; j < stateMatrix.length; j++){
                stateStringRep += stateMatrix[i][j];
                stateArrRep.add(stateMatrix[i][j]);
            }
        }
    }

    public Node(String[] stateMatrix){

        for (String x: stateMatrix) {
            stateStringRep += x;
            stateArrRep.add(x);
        }

        parent = null;
        nodeCost = 0;

        // changes the 1d array into a 2d array
        for(int i = 0 ; i < 3 ; i++){
            state[0][i] = stateMatrix[i];
            if(stateMatrix[i].equals("B")){
                blankPos = "0" + i;
            }
        }
        for(int i = 3 ; i < 6 ; i++){
            state[1][i-3] = stateMatrix[i];
            if(stateMatrix[i].equals("B")){
                blankPos = "1" + (i-3);
            }
        }
        for(int i = 6 ; i < 9 ; i++){
            state[2][i-6] = stateMatrix[i];
            if(stateMatrix[i].equals("B")){
                blankPos = "2" + (i-6);
            }
        }

    }

    public int getFxMan() {
        return fxMan;
    }

    public void setFxMan(int fxMan) {
        this.fxMan = fxMan;
    }

    public int getFxHam() {
        return fxHam;
    }

    public void setFxHam(int fxHam) {
        this.fxHam = fxHam;
    }

    public int getFxPer() {
        return fxPer;
    }

    public void setFxPer(int fxPer) {
        this.fxPer = fxPer;
    }

    public int getFxIndamissible() {
        return fxIndamissible;
    }

    public void setFxIndamissible(int fxIndamissible) {
        this.fxIndamissible = fxIndamissible;
    }

    public int getNodeCost() {
        return nodeCost;
    }

    public void setNodeCost(int nodeCost) {
        this.nodeCost = nodeCost;
    }

    public int getInadmissible() {
        return inadmissible;
    }

    public void setInadmissible(int inadmissible) {
        this.inadmissible = inadmissible;
    }

    public int getHam() {
        return ham;
    }

    public void setHam(int ham) {
        this.ham = ham;
    }

    public int getMan() {
        return man;
    }

    public void setMan(int man) {
        this.man = man;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }

    public void setBlankPos(String blankPos){
        this.blankPos = blankPos;
    }

    public String getStateStringRep() {
        return stateStringRep;
    }

    public String getParentToChildMove() {
        return parentToChildMove;
    }

    public ArrayList<String> getStateArrRep() {
        return stateArrRep;
    }

    public void setParentToChildMove(String parentToChildMove) {
        this.parentToChildMove = parentToChildMove;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public String[][] getState() {
        return state;
    }

    @Override
    public int compareTo(Node o) {
        return this.getMan() - o.getMan();
    }

    // used in the Best First Search algorithm to sort the lists
    public static Comparator<Node> hammingCompare = Comparator.comparingInt(Node::getHam);
    public static Comparator<Node> manhattanCompare = Comparator.comparingInt(Node::getMan);
    public static Comparator<Node> permutationCompare = Comparator.comparingInt(Node::getPer);
    public static Comparator<Node> inadmissibleCompare = Comparator.comparingInt(Node::getInadmissible);

    // used in the A* Search algorithm to sort the lists
    public static Comparator<Node> hammingStarCompare = Comparator.comparingInt((Node::getFxHam));
    public static Comparator<Node> manhattanStarCompare = Comparator.comparingInt((Node::getFxMan));
    public static Comparator<Node> permutationStarCompare = Comparator.comparingInt((Node::getFxPer));
    public static Comparator<Node> inadmissibleStarCompare = Comparator.comparingInt((Node::getFxIndamissible));

    //compares the current node with the node that is being passed
    public boolean compareNodes(Node b){

        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++){
                if(!state[i][j].equals(b.getState()[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

    // Generates all possible children of the parent
    public ArrayList<Node> generateChildren() {

        //Blank is at (0,0) position
        switch (blankPos) {
            case "00" -> {
                String[][] child_r = new String[3][3];
                String[][] child_d = new String[3][3];
                String temp;

                for (int z = 0; z < child_r.length; z++) {
                    child_r[z] = Arrays.copyOf(state[z], state[z].length);
                    child_d[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 0,0 and 0,1 ( right )
                temp = child_r[0][0];
                child_r[0][0] = child_r[0][1];
                child_r[0][1] = temp;

                // swap values from postions 0,0 and 1,0 ( down )
                temp = child_d[0][0];
                child_d[0][0] = child_d[1][0];
                child_d[1][0] = temp;

                Node childR = new Node(child_r);
                Node childD = new Node(child_d);

                childR.setParent(this);
                childD.setParent(this);

                childR.setBlankPos("01");
                childD.setBlankPos("10");

                childR.setParentToChildMove("Right");
                childD.setParentToChildMove("Down");

                children.add(childR);
                children.add(childD);

            }
            case "01" -> { //Blank is at (0,1) position
                String[][] child_r = new String[3][3];
                String[][] child_d = new String[3][3];
                String[][] child_l = new String[3][3];
                String temp;

                for (int z = 0; z < child_r.length; z++) {
                    child_r[z] = Arrays.copyOf(state[z], state[z].length);
                    child_d[z] = Arrays.copyOf(state[z], state[z].length);
                    child_l[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 0,1 and 0,2 ( right )
                temp = child_r[0][1];
                child_r[0][1] = child_r[0][2];
                child_r[0][2] = temp;

                // swap values from postions 0,1 and 1,1 ( down )
                temp = child_d[0][1];
                child_d[0][1] = child_d[1][1];
                child_d[1][1] = temp;

                // swap values from postions 0,1 and 0,0 ( left )
                temp = child_l[0][1];
                child_l[0][1] = child_l[0][0];
                child_l[0][0] = temp;

                Node childR = new Node(child_r);
                Node childD = new Node(child_d);
                Node childL = new Node(child_l);

                childR.setParent(this);
                childD.setParent(this);
                childL.setParent(this);

                childR.setBlankPos("02");
                childD.setBlankPos("11");
                childL.setBlankPos("00");

                childR.setParentToChildMove("Right");
                childD.setParentToChildMove("Down");
                childL.setParentToChildMove("Left");

                children.add(childR);
                children.add(childD);
                children.add(childL);

            }
            case "02" -> { //Blank is at (0,2) position
                String[][] child_d = new String[3][3];
                String[][] child_l = new String[3][3];
                String temp;

                for (int z = 0; z < child_d.length; z++) {
                    child_d[z] = Arrays.copyOf(state[z], state[z].length);
                    child_l[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 0,2 and 1,2 ( down )
                temp = child_d[0][2];
                child_d[0][2] = child_d[1][2];
                child_d[1][2] = temp;

                // swap values from postions 0,2 and 0,1 ( left )
                temp = child_l[0][2];
                child_l[0][2] = child_l[0][1];
                child_l[0][1] = temp;

                Node childD = new Node(child_d);
                Node childL = new Node(child_l);

                childD.setParent(this);
                childL.setParent(this);

                childD.setBlankPos("12");
                childL.setBlankPos("01");

                childD.setParentToChildMove("Down");
                childL.setParentToChildMove("Left");

                children.add(childD);
                children.add(childL);

            }
            case "10" -> { //Blank is at (1,0) position
                String[][] child_r = new String[3][3];
                String[][] child_d = new String[3][3];
                String[][] child_u = new String[3][3];
                String temp;

                for (int z = 0; z < child_r.length; z++) {
                    child_r[z] = Arrays.copyOf(state[z], state[z].length);
                    child_d[z] = Arrays.copyOf(state[z], state[z].length);
                    child_u[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 1,0 and 1,1 ( right )
                temp = child_r[1][0];
                child_r[1][0] = child_r[1][1];
                child_r[1][1] = temp;

                // swap values from postions 1,0 and 2,0 ( down )
                temp = child_d[1][0];
                child_d[1][0] = child_d[2][0];
                child_d[2][0] = temp;

                // swap values from postions 1,0 and 0,0 ( up )
                temp = child_u[1][0];
                child_u[1][0] = child_u[0][0];
                child_u[0][0] = temp;

                Node childR = new Node(child_r);
                Node childD = new Node(child_d);
                Node childU = new Node(child_u);

                childR.setParent(this);
                childD.setParent(this);
                childU.setParent(this);

                childR.setBlankPos("11");
                childD.setBlankPos("20");
                childU.setBlankPos("00");

                childR.setParentToChildMove("Right");
                childD.setParentToChildMove("Down");
                childU.setParentToChildMove("Up");

                children.add(childD);
                children.add(childU);
                children.add(childR);


            }
            case "11" -> { //Blank is at (1,1) position
                String[][] child_r = new String[3][3];
                String[][] child_d = new String[3][3];
                String[][] child_l = new String[3][3];
                String[][] child_u = new String[3][3];
                String temp;

                for (int z = 0; z < child_r.length; z++) {
                    child_r[z] = Arrays.copyOf(state[z], state[z].length);
                    child_d[z] = Arrays.copyOf(state[z], state[z].length);
                    child_u[z] = Arrays.copyOf(state[z], state[z].length);
                    child_l[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 1,1 and 1,2 ( right )
                temp = child_r[1][1];
                child_r[1][1] = child_r[1][2];
                child_r[1][2] = temp;

                // swap values from postions 1,1 and 2,1 ( down )
                temp = child_d[1][1];
                child_d[1][1] = child_d[2][1];
                child_d[2][1] = temp;

                // swap values from postions 1,1 and 0,1 ( up )
                temp = child_u[1][1];
                child_u[1][1] = child_u[0][1];
                child_u[0][1] = temp;

                // swap values from postions 1,1 and 1,0 ( left )
                temp = child_l[1][1];
                child_l[1][1] = child_l[1][0];
                child_l[1][0] = temp;

                Node childR = new Node(child_r);
                Node childD = new Node(child_d);
                Node childU = new Node(child_u);
                Node childL = new Node(child_l);

                childR.setParent(this);
                childD.setParent(this);
                childU.setParent(this);
                childL.setParent(this);

                childR.setBlankPos("12");
                childD.setBlankPos("21");
                childU.setBlankPos("01");
                childL.setBlankPos("10");

                childR.setParentToChildMove("Right");
                childD.setParentToChildMove("Down");
                childL.setParentToChildMove("Left");
                childU.setParentToChildMove("Up");


                children.add(childR);
                children.add(childD);
                children.add(childL);
                children.add(childU);


            }
            case "12" -> { //Blank is at (1,2) position
                String[][] child_d = new String[3][3];
                String[][] child_l = new String[3][3];
                String[][] child_u = new String[3][3];
                String temp;

                for (int z = 0; z < child_d.length; z++) {
                    child_d[z] = Arrays.copyOf(state[z], state[z].length);
                    child_u[z] = Arrays.copyOf(state[z], state[z].length);
                    child_l[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 1,2 and 2,2 ( down )
                temp = child_d[1][2];
                child_d[1][2] = child_d[2][2];
                child_d[2][2] = temp;

                // swap values from postions 1,2 and 0,2 ( up )
                temp = child_u[1][2];
                child_u[1][2] = child_u[0][2];
                child_u[0][2] = temp;

                // swap values from postions 1,2 and 1,1 ( left )
                temp = child_l[1][2];
                child_l[1][2] = child_l[1][1];
                child_l[1][1] = temp;

                Node childD = new Node(child_d);
                Node childU = new Node(child_u);
                Node childL = new Node(child_l);

                childD.setParent(this);
                childU.setParent(this);
                childL.setParent(this);

                childD.setBlankPos("22");
                childU.setBlankPos("02");
                childL.setBlankPos("11");

                childD.setParentToChildMove("Down");
                childL.setParentToChildMove("Left");
                childU.setParentToChildMove("Up");

                children.add(childD);
                children.add(childL);
                children.add(childU);

            }
            case "20" -> { //Blank is at (2,0) position
                String[][] child_r = new String[3][3];
                String[][] child_u = new String[3][3];
                String temp;

                for (int z = 0; z < child_r.length; z++) {
                    child_r[z] = Arrays.copyOf(state[z], state[z].length);
                    child_u[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 2,0 and 2,1 ( right )
                temp = child_r[2][0];
                child_r[2][0] = child_r[2][1];
                child_r[2][1] = temp;

                // swap values from postions 2,0 and 1,0 ( up )
                temp = child_u[2][0];
                child_u[2][0] = child_u[1][0];
                child_u[1][0] = temp;


                Node childR = new Node(child_r);
                Node childU = new Node(child_u);

                childR.setParent(this);
                childU.setParent(this);

                childR.setBlankPos("21");
                childU.setBlankPos("10");

                childR.setParentToChildMove("Right");
                childU.setParentToChildMove("Up");

                children.add(childR);
                children.add(childU);

            }
            case "21" -> { //Blank is at (2,1) position
                String[][] child_r = new String[3][3];
                String[][] child_l = new String[3][3];
                String[][] child_u = new String[3][3];
                String temp;

                for (int z = 0; z < child_r.length; z++) {
                    child_r[z] = Arrays.copyOf(state[z], state[z].length);
                    child_u[z] = Arrays.copyOf(state[z], state[z].length);
                    child_l[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 2,1 and 2,2 ( right )
                temp = child_r[2][1];
                child_r[2][1] = child_r[2][2];
                child_r[2][2] = temp;

                // swap values from postions 2,1 and 1,1 ( up )
                temp = child_u[2][1];
                child_u[2][1] = child_u[1][1];
                child_u[1][1] = temp;

                // swap values from postions 2,1 and 2,0 ( left )
                temp = child_l[2][1];
                child_l[2][1] = child_l[2][0];
                child_l[2][0] = temp;

                Node childR = new Node(child_r);
                Node childU = new Node(child_u);
                Node childL = new Node(child_l);

                childR.setParent(this);
                childU.setParent(this);
                childL.setParent(this);

                childR.setBlankPos("22");
                childU.setBlankPos("11");
                childL.setBlankPos("20");

                childR.setParentToChildMove("Right");
                childL.setParentToChildMove("Left");
                childU.setParentToChildMove("Up");

                children.add(childR);
                children.add(childL);
                children.add(childU);

            }
            case "22" -> { //Blank is at (2,2) position
                String[][] child_l = new String[3][3];
                String[][] child_u = new String[3][3];
                String temp;

                for (int z = 0; z < child_l.length; z++) {
                    child_u[z] = Arrays.copyOf(state[z], state[z].length);
                    child_l[z] = Arrays.copyOf(state[z], state[z].length);
                }

                // swap values from postions 2,2 and 1,2 ( up )
                temp = child_u[2][2];
                child_u[2][2] = child_u[1][2];
                child_u[1][2] = temp;

                // swap values from postions 2,2 and 2,1 ( left )
                temp = child_l[2][2];
                child_l[2][2] = child_l[2][1];
                child_l[2][1] = temp;

                Node childU = new Node(child_u);
                Node childL = new Node(child_l);

                childU.setParent(this);
                childL.setParent(this);

                childU.setBlankPos("12");
                childL.setBlankPos("21");

                childL.setParentToChildMove("Left");
                childU.setParentToChildMove("Up");

                children.add(childL);
                children.add(childU);

            }
        }

        return children;
    }



}
