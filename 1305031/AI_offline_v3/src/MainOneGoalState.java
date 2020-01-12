import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class MainOneGoalState {


    public static int n;
    public static PriorityQueue<Node> minPQ;
    public static HashSet<Node> openSet;
    public static HashSet<Node> closedSet;






    public static int[][] takingInput() throws  FileNotFoundException {

        File file = new File("input1.txt");
        Scanner sc = new Scanner(file);

        int[][] inputArray = null;

        while (sc.hasNext())
        {

            n = sc.nextInt();


            inputArray = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    inputArray[i][j] = sc.nextInt();
                }
            }
        }

        return inputArray;

    }



    public static void main(String[] args) throws FileNotFoundException {

        int[][] inputArray = takingInput();


        Node root = new Node(n, inputArray);
        int[][] goal = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int value = i+1;
                goal[i][j]=value;
            }
        }

        Node goalState = new Node(n,goal);
        openSet = new HashSet<>();
        closedSet = new HashSet<>();

        minPQ = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare((int) o1.getfScore(), (int) o2.getfScore());
            }
        });




        PathRetrace pathRetrace = new PathRetrace();
        ArrayList<Node> pathOfOptimalSolution = new ArrayList<>();

        minPQ.add(root);
        openSet.add(root);

        root.setgScore(0);
        root.setfScore(root.heuristicValue(2,goalState));
        root.setParent(null);




        while (!minPQ.isEmpty() ) {

            Node current = minPQ.poll();


            if (current.sameArray(goalState)) {
                System.out.println("Begin Path Retracing");

                pathOfOptimalSolution = pathRetrace.pathConstruction(current);

                break;
            }

            closedSet.add(current);


            //UP
            for (int i = 0; i < n; i++) {
                Node neighbor = new Node(n, current.getRubik2D());
                neighbor.moveTiles(i, 1,true);
                neighbor.setParent(current);

                if (!openSet.contains(neighbor)) {
                    if (closedSet.contains(neighbor))
                        continue;

                    double modifiedValue = current.getgScore() + 1;

                    if (modifiedValue >= neighbor.getgScore())
                        continue;

                    settingHeuristicValues(neighbor, modifiedValue, goalState);

                    childAction(neighbor,i,1);

                    addingToDataStructures(neighbor);
                }
            }

            //DOWN
            for (int i = 0; i < n; i++) {
                Node neighbor = new Node(n, current.getRubik2D());
                neighbor.moveTiles(i, 2,true);
                neighbor.setParent(current);
                if (!openSet.contains(neighbor)) {
                    if (closedSet.contains(neighbor)) {

                        continue;
                    }


                    double modifiedValue = current.getgScore() + 1;

                    if (modifiedValue >= neighbor.getgScore())
                        continue;

                    settingHeuristicValues(neighbor, modifiedValue, goalState);

                    childAction(neighbor,i,2);

                    addingToDataStructures(neighbor);
                }
            }

            //LEFT
            for (int i = 0; i < n; i++) {
                Node neighbor = new Node(n, current.getRubik2D());
                neighbor.moveTiles(i, 3,false);
                neighbor.setParent(current);

                if (!openSet.contains(neighbor)) {
                    if (closedSet.contains(neighbor))
                        continue;

                    double modifiedValue = current.getgScore() + 1;

                    if (modifiedValue >= neighbor.getgScore())
                        continue;

                    settingHeuristicValues(neighbor, modifiedValue, goalState);

                    childAction(neighbor,i,3);
                    addingToDataStructures(neighbor);
                }
            }

            //RIGHT
            for (int i = 0; i < n; i++) {
                Node neighbor = new Node(n, current.getRubik2D());
                neighbor.moveTiles(i, 4,false);
                neighbor.setParent(current);
                if (!openSet.contains(neighbor)) {
                    if (closedSet.contains(neighbor))
                        continue;

                    double modifiedValue = current.getgScore() + 1;

                    if (modifiedValue >= neighbor.getgScore())
                        continue;


                    settingHeuristicValues(neighbor, modifiedValue, goalState);

                    childAction(neighbor,i,4);


                    addingToDataStructures(neighbor);
                }

            }
            Node s = minPQ.peek();


        }

        pathRetrace.printPath(pathOfOptimalSolution );

    }

    public static void nodeProcessing(Node current, Node goalState, int moveType) {

        for (int i = 0; i < n; i++) {
            Node neighbor = new Node(n, current.getRubik2D());
            neighbor.moveTiles(i, moveType, false);
            neighbor.setParent(current);
            if (!openSet.contains(neighbor)) {
                if (closedSet.contains(neighbor))
                    continue;

                double modifiedValue = current.getgScore() + 1;

                if (modifiedValue >= neighbor.getgScore())
                    continue;


                settingHeuristicValues(neighbor, modifiedValue, goalState);

                childAction(neighbor, i, moveType);


                addingToDataStructures(neighbor);
            }
        }
    }

    public static void settingHeuristicValues( Node neighbor, double modifiedValue,Node goalState)
    {


        neighbor.setgScore(modifiedValue);
        neighbor.setfScore(neighbor.getgScore() + neighbor.heuristicValue(2,goalState));
    }

    public static void addingToDataStructures(Node child){
        openSet.add(child);
        minPQ.add(child);
    }
    public static void childAction(Node neighbor,int i, int moveType){
        int x = i+1;
        if(moveType == 1)
        {
            neighbor.setActionTaken("Column " + x + "goes UP");
        }
        else if(moveType == 2)
        {
            neighbor.setActionTaken("Column " + x + "goes DOWN");
        }
        else if(moveType == 3)
        {
            neighbor.setActionTaken("Row " + x + "goes LEFT");
        }
        else if(moveType == 4)
        {
            neighbor.setActionTaken("Row " + x + "goes RIGHT");
        }
    }
}


