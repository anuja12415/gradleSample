package hello;

public class PathBetweenCellsInMatrix {

    public static void main(String[] args){
        int matrix[][] = {{0, 3, 0, 1},
                {3, 0, 3, 3},
                {2, 3, 3, 3},
                {0, 3, 3, 3}};

        findPath(matrix, 4);

    }

    static void findPath(int matrix[][], int n){
        boolean visited[][] = new boolean[n][n];

        boolean flag = false;
        for(int i=0; i<n; i++){
            for(int j=0; j< n; j++){
                if(matrix[i][j] == 1){
                    if(isPath(matrix, i, j, visited)){
                        flag = true;
                        break;
                    }
                }
            }
        }
        if(flag)
            System.out.println("Path is present");
        else
            System.out.println("No Path present");
    }

    static boolean isSafe(int i, int j, int matrix[][]){
        if( i>=0 && i< matrix.length && j>=0 && j< matrix[0].length ){
            return true;
        }
        return false;
    }

    static boolean isPath(int matrix[][], int i, int j, boolean visited[][]) {
        if (isSafe(i, j, matrix) && matrix[i][j] != 0 && !visited[i][j]) {
            visited[i][j] = true;
            if (matrix[i][j] == 2)
                return true;

            boolean right = isPath(matrix, i, j + 1, visited);
            if (right)
                return true;

            boolean left = isPath(matrix, i, j - 1, visited);
            if (left)
                return true;

            boolean up = isPath(matrix, i-1, j, visited);
            if(up)
                return true;

            boolean down = isPath(matrix, i+1, j, visited);
            if(down)
                return true;
        }
        return false;
    }
}
