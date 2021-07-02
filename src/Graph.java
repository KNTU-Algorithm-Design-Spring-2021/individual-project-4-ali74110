import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
                if (adjList[forbidden].contains(localPathList.get(i))){
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
    static void getGragh(Graph graph){
        Scanner scanner = new Scanner(System.in);
        System.out.println("number of vertices:");
        int verticsNum = scanner.nextInt();
        int maxEdgesNum = factorial(verticsNum) / (factorial(verticsNum-2));
        graph = new Graph(verticsNum);
        System.out.println("vertics from 0 to " + (verticsNum-1) + "initialized.\n");
        System.out.println("add edges:(to stop enter -1)");
        int from, to;
        for (int i = 0; i < maxEdgesNum; i++) {
//            System.out.println("from:");
            from = scanner.nextInt();
//            System.out.println("to:");
            to = scanner.nextInt();
            if (from < 0 || to < 0 || from >= verticsNum || to >= verticsNum){
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
    
    // Driver program
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Graph graph = null;
        getGragh(graph);

        // arbitrary source
        int from, to, forbidden;
        System.out.println("find path from:\n");
        from = scanner.nextInt();
        System.out.println("to:\n");
        to = scanner.nextInt();
        System.out.println("forbidden vertice:\n");
        forbidden = scanner.nextInt();

        System.out.println(
                "Following are all different paths from "
                        + from + " to " + to);
        graph.printAllPaths(from, to, forbidden);
    }
}

