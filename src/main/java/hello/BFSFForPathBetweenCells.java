package hello;

import java.util.LinkedList;
import java.util.Queue;

class GraphMatrix {
        int v;
        LinkedList<Integer> adjListArray[];

        GraphMatrix(int v) {
            this.v = v;
            adjListArray = new LinkedList[v];

            for (int i = 0; i < v; i++) {
                adjListArray[i] = new LinkedList<Integer>();
            }

        }

        void addEdge(int source, int destination){
            adjListArray[source].add(destination);
        }

       static boolean BFSCheckPath(GraphMatrix g, int source, int destination){
            boolean[] visited = new boolean[g.v];
            if(source == -1 || destination == -1){
                System.out.println("Either Source or destination or both are absent");
            }
           LinkedList<Integer> queue = new LinkedList<>();
           queue.add(source);
           visited[source] = true;

           while(!queue.isEmpty()){
                int key = queue.poll();
                for(int element : g.adjListArray[key]){
                    if(element == destination){
                        return true;
                    }

                    if(!visited[element]){
                        visited[element] = true;
                        queue.add(element);
                    }
                }
           }

            return false;
       }

       static boolean isPath(int matrix[][], int n){
        int k = 0;
        int source = -1;
        int destination = -1;
         GraphMatrix g = new GraphMatrix(n*n);
        for(int i =0;i < n; i++){
            for(int j=0; j<n; j++){

                if(matrix[i][j] != 0) {
                    //Right
                    if (g.isSafe(i, j + 1, n, matrix)) {
                        g.addEdge(k, k + 1);
                    }
                    //Left
                    if (g.isSafe(i, j - 1, n, matrix)) {
                        g.addEdge(k, k - 1);
                    }
                    //Up
                    if (g.isSafe(i - 1, j, n, matrix)) {
                        g.addEdge(k, k - n);
                    }
                    //Down
                    if (g.isSafe(i + 1, j, n, matrix)) {
                        g.addEdge(k, k + n);
                    }
                }

                if(matrix[i][j] ==1){
                    source = k;
                }
                else if(matrix[i][j] == 2){
                    destination = k;
                }
                k++;
            }
        }
        return BFSCheckPath(g, source, destination);
     }

     boolean isSafe(int i, int j, int n , int matrix[][]){
        if(i >=0 && i< n && j>=0 && j<n &&  matrix[i][j] != 0){
            return true;
        }else{
            return false;
        }
     }

    public static void main(String[] args){
        int matrix[][] = {{0, 3, 0, 1},
                {3, 0, 3, 3},
                {2, 3, 3, 3},
                {0, 3, 3, 3}};
       if(isPath(matrix, matrix.length)){
           System.out.print("Path Exists");
       }else{
           System.out.print("Path does not exist");
       }
    }

    }
