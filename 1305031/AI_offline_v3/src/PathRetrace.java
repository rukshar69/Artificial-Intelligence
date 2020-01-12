import java.util.ArrayList;

/**
 * Created by Rukshar Alam on 10/7/2017.
 */
public class PathRetrace {

    ArrayList<Node> pathList;
    PathRetrace(){

    }


    public  ArrayList<Node> pathConstruction(Node current){

        Node node = current;
        pathList = new ArrayList<>();

        pathList.add(current);

        while(node.getParent()!=null){

            node=node.getParent();
            pathList.add(node);
        }
        return pathList;
    }

    public  void printPath(ArrayList<Node> path){

        int sizeOfPath = (path.size()-1);

        System.out.println("Number of moves for this puzzle: "+ sizeOfPath );

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i=sizeOfPath;i>=0;i--){ //back ttarcking

            Node current = path.get(i);

            System.out.println();
            System.out.println();
            current.printActions();
            System.out.println();
            current.printRubikBoard();
            System.out.println();
            System.out.println();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("THE END");
    }
}
