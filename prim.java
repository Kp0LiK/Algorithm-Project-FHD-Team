import java.util.*;
import java.lang.*;
import java.io.*;

class Prim {
    //in this function we will find index of minimal key value and return this
    int minKey(int key[], Boolean mstSet[], int N)
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < N; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    void primMST(int graph[][], int N)
    {
        //we will use array parent, to save our edges for minimal spanning tree
        int parent[] = new int[N];
        //parent[1] = 0
        //parent[2] = 1

        //create array to save value from our minimal spanning tree, to another vertices
        int key[] = new int[N];

        //create boolean array to check which vertices we add to our minimal spanning tree
        Boolean mstSet[] = new Boolean[N];

        //set for all elements in key and mtSet infinite and false
        for (int i = 0; i < N; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        //set zero for our first vertex and he has no parent because he is the first in our minimal spanning tree
        key[0] = 0;
        parent[0] = -1;

        //we will update our minimal spanning tree n - 1 times. Every count we will add vertix to our subgraph
        for (int count = 0; count < N - 1; count++) {
            int u = minKey(key, mstSet, N);

            //set u vertix true because we add this to our subgraph
            mstSet[u] = true;

            //update key array, if we can
            for (int v = 0; v < N; v++){
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        //rewrite our graph array
        int newGraph[][] = new int[N][N];
        /*
        {
        {0, 2, 0, 6, 0}
        {2, 0, 3, 5, 0}
        {0, 3, 0, 0, 0}
        {6, 5, 0, 0, 0}
        {0, 0, 0, 0, 0}
        }
        */
        for(int i = 1; i < N; i++){
            newGraph[i][parent[i]] = graph[i][parent[i]];
            newGraph[parent[i]][i] = graph[i][parent[i]];
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                graph[i][j] = newGraph[i][j];
            }
        }

    }

    public static void main(String[] args)
    {
        Prim t = new Prim();
        Scanner inp = new Scanner(System.in);

        //Scanning with your data
        /*int n = inp.nextInt();
        int m = inp.nextInt();
        int graph[][] = new int[n][n];
        for(int i = 0; i < m; i++){
            int u = inp.nextInt();
            int v = inp.nextInt();
            int val = inp.nextInt();
            graph[u][v] = val;
            graph[v][u] = val;
        }*/

        //default data
        int n = 5;
        int graph[][] = new int[][] {
                { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

        //start Prom algorithm
        t.primMST(graph, n);

        //print graph
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
