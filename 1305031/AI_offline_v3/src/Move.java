/**
 * Created by Rukshar Alam on 10/7/2017.
 */
public class Move {
    int dimension;
    Move(int dimension)
    {
        this.dimension = dimension;

    }

    public int[][] moveUp(int col,int[][] rubik2D){

        int firstElementOfColumn = rubik2D[0][col];

        for(int i=1;i<dimension;i++){

            rubik2D[i-1][col]=rubik2D[i][col];
        }
        rubik2D[dimension-1][col]=firstElementOfColumn;

        return rubik2D;

    }


    public int[][] moveDown(int col,int[][] rubik2D){

        int lastElementOfColumn = rubik2D[dimension-1][col];

        for(int i=dimension-1;i>0;i--){

            rubik2D[i][col]=rubik2D[i-1][col];
        }
        rubik2D[0][col]=lastElementOfColumn;
        return rubik2D;

    }

    public int[][] moveLeft(int row,int[][] rubik2D){

        int firstElementOfRow = rubik2D[row][0];

        for(int i=1;i<dimension;i++){

            rubik2D[row][i-1]=rubik2D[row][i];
        }
        rubik2D[row][dimension-1]=firstElementOfRow;
        return rubik2D;
    }

    public int[][] moveRight(int row,int[][] rubik2D){

        int lastElementOfRow = rubik2D[row][dimension-1];
        for(int i=dimension-1;i>0;i--){

            rubik2D[row][i]=rubik2D[row][i-1];
        }
        rubik2D[row][0] = lastElementOfRow;
        return rubik2D;

    }
}
