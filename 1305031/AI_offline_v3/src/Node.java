import java.util.Arrays;

/**
 * Created by Rukshar Alam on 10/7/2017.
 */
public class Node {

    int infinity = Integer.MAX_VALUE;
    Move moveClass;

    int[][] rubik2D;
    int dimension;


    //for heuristics
    Heuristics heuristics;
    double fScore;
    double gScore;



    //for path creation
    String actionTaken;
    Node parent;




    public void rubikAssignment(int[][] rubik){

        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++)
            {
                this.rubik2D[i][j]=rubik[i][j];
            }
        }
    }



    public Node(int n, int[][] rubik){

        this.rubik2D = new int[n][n];

        this.dimension = n;

        rubikAssignment(rubik);

        parent = null;
        actionTaken ="";

        fScore=infinity;

        gScore=infinity;

        moveClass = new Move(n);

        heuristics = new Heuristics(n);


    }



    void printRubikBoard(){
        System.out.println();

        for(int i=0;i<dimension;i++){

            for(int j=0;j<dimension;j++){

                System.out.print( rubik2D[i][j] + " " );
            }
            System.out.println();
        }
        System.out.println();
    }

    void printActions(){
        if(actionTaken!=null) {
            System.out.println(actionTaken);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node))
        {

            return false; //not related object
        }

        Node temp = (Node) o; //type casting
        boolean result = sameArray(temp);
        return result;
    }

    public boolean sameArray(Node goal)
    {
        boolean result = true;
        int[][] dummyGoalArray = goal.getRubik2D();

        for(int i=0;i<dimension;i++){

            for(int j=0;j<dimension;j++){

                if(this.rubik2D[i][j]==dummyGoalArray[i][j])
                {
                    //ignore
                }
                else {
                    result = false;
                }
            }
        }
        return result;
    }



    public int[][] getRubik2D() {
        return rubik2D;
    }

    public void setRubik2D(int[][] rubik2D) {
        this.rubik2D = rubik2D;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public double getfScore() {
        return fScore;
    }

    public void setfScore(double fScore) {
        this.fScore = fScore;
    }

    public double getgScore() {
        return gScore;
    }

    public void setgScore(double gScore) {
        this.gScore = gScore;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }



    public void moveTiles(int segment , int dir, boolean rowOrCol) //true for column, false for row . segment means
    //whether to manipulate row or column
    {
        if(rowOrCol) //colum up or down : dir 1 for up, dir 2 for down
        {
            int col = segment;

            if(dir==1){ //UP
                //moveUp(col);
                setRubik2D(moveClass.moveUp(col,rubik2D));
            }
            else if(dir==2){ //DOWN
                //moveDown(col);
                setRubik2D(moveClass.moveDown(col,rubik2D));
            }
        }
        else //row left or right: dir 3 for left , dir 4 for right
        {
            int row = segment;
            if(dir==3){ //LEFT
                setRubik2D(moveClass.moveLeft(row,rubik2D));
            }
            else if(dir==4){ //RIGHT
                setRubik2D(moveClass.moveRight(row,rubik2D));
            }
        }
    }


    public double heuristicValue(int type, Node goalState) //type 1 mismatch , type 2 manhattan , type 3 multiple manhattan
    {
        double resultHeuristic=0;
        if(type == 1)
        {
            resultHeuristic = heuristics.mismatch(goalState, this);

        }
        else if(type == 2)
        {
            resultHeuristic = heuristics.manhattanHeuristics(goalState, this);
        }
        else if(type == 3)
        {
            resultHeuristic = heuristics.multipleGoalStateManhattan(goalState, this);

        }
        return resultHeuristic;
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(rubik2D);
    }




}
