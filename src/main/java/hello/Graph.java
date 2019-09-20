package hello;

import java.util.LinkedList;

public class Graph {
    int v;
    LinkedList<Integer> adjListArray[];

    Graph(int v){
        this.v = v;
        adjListArray = new LinkedList[v];

        for(int i =0; i < v; i++){
            adjListArray[i] = new LinkedList<Integer>();
        }

    }

    void addEdge(int src, int dest){
        //Add an edge from source to destination
        this.adjListArray[src].add(dest);

        //Add an edge from dest to src because we are constructing an undirected Graph
//        this.adjListArray[dest].add(src);
    }

    void printGraph(){
        for(int i = 0; i< this.v; i++){
            this.adjListArray[i].forEach(System.out::print);
            System.out.println();
        }
    }

    void BFS(int first){
        boolean[] visited = new boolean[v];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[first] = true;
        queue.add(first);
        while(!queue.isEmpty()){
            int s = queue.poll();
            System.out.println(s);
            adjListArray[s].forEach(n -> {
                if(!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            });
        }

    }

    void DFSUtil(int source , boolean[] visited){
        visited[source] = true;
        System.out.println(source);

        adjListArray[source].forEach(n -> {
            if(!visited[n]){
                DFSUtil(n, visited);
            }
        });
    }

    void DFS(int source){
        boolean[] visitedDFs = new boolean[this.v];
        DFSUtil(source, visitedDFs);
    }

    boolean isCyclicUtil(int source, boolean[] visited, boolean[] revstack){
        if(revstack[source])
            return true;

        if(visited[source])
            return false;

        visited[source] = true;
        revstack[source] = true;

        for(int element : this.adjListArray[source]){
                if(isCyclicUtil(element, visited, revstack))
                    return true;
        }
        revstack[source] = false;

        return false;
    }

    boolean isCyclicDirected(){
        boolean[] visted = new boolean[v];
        boolean[] revstack = new boolean[v];

        for(int i =0; i< v; i++){
            if(isCyclicUtil(2, visted, revstack))
                return true;
        }
        return false;
    }

    public static void main(String[] args){
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        graph.printGraph();
        System.out.println("BFS of the Graph");
        graph.BFS(2);

        System.out.println("DFS of the Graph");
        graph.DFS(2);
        if(graph.isCyclicDirected()){
            System.out.println("Cycle exists");
        }else {
            System.out.println("No Cycle in graph");
        }
    }


}
