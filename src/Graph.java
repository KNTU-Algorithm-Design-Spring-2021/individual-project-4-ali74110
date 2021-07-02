import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    // No. of vertices in graph
    private int verticesNum;

    // adjacency list
    private ArrayList<Integer>[] adjList;

    // Constructor
    public Graph(int verticesNum)
    {

        // initialise vertex count
        this.verticesNum = verticesNum;

        // initialise adjacency list
        initAdjList();
    }

    private void initAdjList()
    {
        adjList = new ArrayList[verticesNum];

        for (int i = 0; i < verticesNum; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to)
    {
        adjList[from].add(to);
    }

    public void printAllPaths(int startV, int endV, int forbidden)
    {
        boolean[] isVisited = new boolean[verticesNum];
        ArrayList<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(startV);

        // Call recursive utility
        printAllPathsUtil(startV, endV, forbidden, isVisited, pathList);
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private void printAllPathsUtil(Integer from, Integer to, Integer forbidden, boolean[] isVisited, List<Integer> localPathList)
    {
        if (from.equals(to)) {
            boolean inWolfWay = false;
            for (int i = 0; i < localPathList.size() ; i++) {
                if (adjList[forbidden].contains(localPathList.get(i)) || adjList[localPathList.get(i)].contains(forbidden)){
                    inWolfWay = true;
                }
            }
            if (!inWolfWay){
                System.out.println(localPathList);
            }
            // if match found then no need to traverse more till depth
            return;
        }

        // Mark the current node
        isVisited[from] = true;

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjList[from]) {
            if (!isVisited[i]) {
                // store current node
                // in path[]
                localPathList.add(i);
                printAllPathsUtil(i, to, forbidden, isVisited, localPathList);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[from] = false;
    }


    //factorial
    public static int factorial(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorial(n - 1);
    }

    //get graph vertices from user
    static void getUserGragh(Graph graph, int verticesNum){
        Scanner scanner = new Scanner(System.in);
        int maxEdgesNum = factorial(verticesNum) / (factorial(verticesNum-2));
        graph = new Graph(verticesNum);
        System.out.println("vertics from 0 to " + (verticesNum-1) + "initialized.\n");
        System.out.println("add edges:(to stop enter -1)");
        int from, to;
        for (int i = 0; i < maxEdgesNum; i++) {
            System.out.println("from:");
            from = scanner.nextInt();
            System.out.println("to:");
            to = scanner.nextInt();
            if (from < 0 || to < 0 || from >= verticesNum || to >= verticesNum){
                if (from == -1 || to == -1){
                    break;
                }
                i--;
                continue;
            }
            graph.addEdge(from, to);
        }
        //scanner.close();
    }

    public static void initGraph(Graph graph){
        //graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 0);
    }
    
    // Driver program
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int from, to, forbidden;
        System.out.println("number of vertices:");
        int verticesNum = scanner.nextInt();
        Graph graph = new Graph(verticesNum);
        getUserGragh(graph, verticesNum);

        //        initGraph(graph);

        // arbitrary source

        System.out.println("find path from:\n");
        from = scanner.nextInt();
        System.out.println("to:\n");
        to = scanner.nextInt();
        System.out.println("forbidden vertice:\n");
        forbidden = scanner.nextInt();
//        from = 0;
//        to = 3;
//        forbidden = 4;
        System.out.println(
                "Following are all different paths from "
                        + from + " to " + to);
        graph.printAllPaths(from, to, forbidden);
    }
}

