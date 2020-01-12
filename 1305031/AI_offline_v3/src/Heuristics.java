import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Created by Rukshar Alam on 10/7/2017.
 */
public class Heuristics {

    int dimension;
    Heuristics(int dimension)
    {
        this.dimension = dimension;

    }

    public double mismatch(Node goal, Node underConsideration){

        double heuristicValue ;
        int[][] goalRubikBoard = goal.getRubik2D();
        int[][] underConsiderationRubikBoard = underConsideration.getRubik2D();

        int countMismatch=0;

        for(int i=0;i<dimension;i++){

            for(int j=0;j<dimension;j++){

                if(underConsiderationRubikBoard[i][j]!=goalRubikBoard[i][j]){
                    countMismatch++;
                }
            }
        }
        heuristicValue = (double)countMismatch/(double) dimension;

        return heuristicValue;
    }

    public double manhattanHeuristics(Node goal, Node underConsideration){

        int[][] goalBoard = goal.getRubik2D();
        int[][] underConsiderationRubikBoard = underConsideration.getRubik2D();

        int cost = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                int value = underConsiderationRubikBoard[i][j];
                int positionInTheGoalMatrix = value - 1;// we are using 0 based index.....
                int choice = (positionInTheGoalMatrix <= i)?1:0; //1 for goal position being less than actual position, 0 for otherwise

                switch (choice){
                    case 1:
                        int simple = positionInTheGoalMatrix - i;
                        int wrapping = dimension - i + positionInTheGoalMatrix;
                        int simpleDistance = abs(simple);
                        int wrappingDistance = abs(wrapping);
                        int minVal = min(simpleDistance, wrappingDistance);
                        cost =cost+ minVal;
                        break;
                    case 0:
                        int simple2 =positionInTheGoalMatrix - i;
                        int wrapping2 = dimension + i - positionInTheGoalMatrix;

                        int simpleDistance2 = abs(simple2);
                        int wrappingDistance2 = abs(wrapping2);
                        int minVal2 = min(simpleDistance2, wrappingDistance2);
                        cost =cost+ minVal2; break;
                }

            }
        }

        double manhattanValue = (double) cost / (double)dimension;

        double rectified =  Math.ceil(manhattanValue);
        return rectified;
    }

    int processUp(int[][] underConsiderationRubikBoard){
        int up  = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int value = underConsiderationRubikBoard[i][j];
                int positionInTheGoalMatrix = value - 1;

                int choice = (positionInTheGoalMatrix <= i)?1:0; //1 for goal position being less than actual position, 0 for otherwise

                switch (choice){
                    case 1:
                        int simple = positionInTheGoalMatrix - i;
                        int wrapping = dimension - i + positionInTheGoalMatrix;
                        int simpleDistance = abs(simple);
                        int wrappingDistance = abs(wrapping);
                        int minVal = min(simpleDistance, wrappingDistance);
                        up =up+ minVal;
                        break;
                    case 0:
                        int simple2 =positionInTheGoalMatrix - i;
                        int wrapping2 = dimension + i - positionInTheGoalMatrix;

                        int simpleDistance2 = abs(simple2);
                        int wrappingDistance2 = abs(wrapping2);
                        int minVal2 = min(simpleDistance2, wrappingDistance2);
                        up =up+ minVal2; break;
                }

            }
        }
        return up;
    }


    int processDown(int[][] underConsiderationRubikBoard){
        int down = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                int value = underConsiderationRubikBoard[i][j];

                int positionInTheGoalMatrix = dimension-value;
                int choice = (positionInTheGoalMatrix <= i)?1:0; //1 for goal position being less than actual position, 0 for otherwise

                switch (choice){
                    case 1:
                        int simple = positionInTheGoalMatrix - i;
                        int wrapping = dimension - i + positionInTheGoalMatrix;
                        int simpleDistance = abs(simple);
                        int wrappingDistance = abs(wrapping);
                        int minVal = min(simpleDistance, wrappingDistance);
                        down =down+ minVal;
                        break;
                    case 0:
                        int simple2 =positionInTheGoalMatrix - i;
                        int wrapping2 = dimension + i - positionInTheGoalMatrix;

                        int simpleDistance2 = abs(simple2);
                        int wrappingDistance2 = abs(wrapping2);
                        int minVal2 = min(simpleDistance2, wrappingDistance2);
                        down =down+ minVal2; break;
                }

            }
        }
        return  down;
    }

    int processLeft(int[][] underConsiderationRubikBoard){
        int left = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                int value = underConsiderationRubikBoard[i][j];


                int positionInTheGoalMatrix = value - 1;

                int choice = (positionInTheGoalMatrix <= i)?1:0; //1 for goal position being less than actual position, 0 for otherwise

                switch (choice){
                    case 1:
                        int simple = positionInTheGoalMatrix - j;
                        int wrapping = dimension - j + positionInTheGoalMatrix;
                        int simpleDistance = abs(simple);
                        int wrappingDistance = abs(wrapping);
                        int minVal = min(simpleDistance, wrappingDistance);
                        left =left+ minVal;
                        break;
                    case 0:
                        int simple2 =positionInTheGoalMatrix - j;
                        int wrapping2 = dimension + j - positionInTheGoalMatrix;

                        int simpleDistance2 = abs(simple2);
                        int wrappingDistance2 = abs(wrapping2);
                        int minVal2 = min(simpleDistance2, wrappingDistance2);
                        left =left+ minVal2; break;
                }


            }
        }
        return  left;
    }

    int processRight(int[][] underConsiderationRubikBoard){
        int right = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                int value =underConsiderationRubikBoard[i][j];

                int positionInTheGoalMatrix = dimension-value;

                int choice = (positionInTheGoalMatrix <= i)?1:0;

                switch (choice){
                    case 1:
                        int simple = positionInTheGoalMatrix - j;
                        int wrapping = dimension - j + positionInTheGoalMatrix;
                        int simpleDistance = abs(simple);
                        int wrappingDistance = abs(wrapping);
                        int minVal = min(simpleDistance, wrappingDistance);
                        right =right+ minVal;
                        break;
                    case 0:
                        int simple2 =positionInTheGoalMatrix - j;
                        int wrapping2 = dimension + j - positionInTheGoalMatrix;

                        int simpleDistance2 = abs(simple2);
                        int wrappingDistance2 = abs(wrapping2);
                        int minVal2 = min(simpleDistance2, wrappingDistance2);
                        right =right+ minVal2; break;
                }



            }
        }
        return  right;
    }


    public double multipleGoalStateManhattan(Node goal,Node underConsideration) {
        int[][] goalBoard = goal.getRubik2D();
        int[][] underConsiderationRubikBoard = underConsideration.getRubik2D();
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        up = processUp(underConsiderationRubikBoard);


        down = processDown(underConsiderationRubikBoard);

        left=processLeft(underConsiderationRubikBoard);
        right = processRight(underConsiderationRubikBoard);



        double doubleDimension = (double) dimension;

        double u = Math.ceil((double)up/doubleDimension);

        double d = Math.ceil((double)down/doubleDimension);

        double l = Math.ceil((double)left/doubleDimension);

        double r = Math.ceil((double)right/doubleDimension);

        double minUpDown = min(u,d);
        double minLR = min(l,r);
        double minVal = min(minUpDown,minLR);

        double rectified =  Math.ceil(minVal);
        return rectified;
        //return retRight;

    }

}
