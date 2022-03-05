import java.util.ArrayList;
import java.util.Arrays;

public class Node {

    private String[][] state = new String[3][3]; // the state of the puzzle represented as a 3 by 3 matrix
    private ArrayList<Node> children = new ArrayList<>(); // the generated children of this node
    private String[][] parent; // the parent of this node
    public String blankPos; // gets the position of the blank space

    public Node(String[][] stateMatrix){
        state = stateMatrix;
        parent = null;
    }

    public Node(String[] stateMatrix){

        parent = null;

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

    public void setBlankPos(String blankPos){
        this.blankPos = blankPos;
    }

    public String[][] getParent(){
        return parent;
    }

    public void setParent(String[][] parent){
        this.parent = parent;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public String[][] getState() {
        return state;
    }

    public void setState(String[][] state) {
        this.state = state;
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

                childR.setParent(state);
                childD.setParent(state);

                childR.setBlankPos("01");
                childD.setBlankPos("10");


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

                childR.setParent(state);
                childD.setParent(state);
                childL.setParent(state);

                childR.setBlankPos("02");
                childD.setBlankPos("11");
                childL.setBlankPos("00");

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

                childD.setParent(state);
                childL.setParent(state);

                childD.setBlankPos("12");
                childL.setBlankPos("01");

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

                childR.setParent(state);
                childD.setParent(state);
                childU.setParent(state);

                childR.setBlankPos("11");
                childD.setBlankPos("20");
                childU.setBlankPos("00");

                children.add(childR);
                children.add(childD);
                children.add(childU);

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

                childR.setParent(state);
                childD.setParent(state);
                childU.setParent(state);
                childL.setParent(state);

                childR.setBlankPos("12");
                childD.setBlankPos("21");
                childU.setBlankPos("01");
                childL.setBlankPos("10");

                children.add(childR);
                children.add(childD);
                children.add(childU);
                children.add(childL);

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

                childD.setParent(state);
                childU.setParent(state);
                childL.setParent(state);

                childD.setBlankPos("22");
                childU.setBlankPos("02");
                childL.setBlankPos("11");

                children.add(childD);
                children.add(childU);
                children.add(childL);

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

                childR.setParent(state);
                childU.setParent(state);

                childR.setBlankPos("21");
                childU.setBlankPos("10");

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

                childR.setParent(state);
                childU.setParent(state);
                childL.setParent(state);

                childR.setBlankPos("22");
                childU.setBlankPos("11");
                childL.setBlankPos("20");

                children.add(childR);
                children.add(childU);
                children.add(childL);

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

                childU.setParent(state);
                childL.setParent(state);

                childU.setBlankPos("12");
                childL.setBlankPos("21");

                children.add(childU);
                children.add(childL);

            }
        }

        return children;
    }



}
