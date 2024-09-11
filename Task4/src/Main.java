import java.util.*;

class Findminimumvalidpath {
    static class Edge {
        int destinationvertexvalue, weightvalue;

        Edge(int destinationvertexvalue, int weightvalue) {
            this.destinationvertexvalue = destinationvertexvalue;
            this.weightvalue = weightvalue;
        }
    }

    static class State implements Comparable<State> {
        int node, cost, prevweightvalue;
        boolean visitedspecialnodevalue;
        List<Integer> path;

        State(int node, int cost, boolean visitedspecialval, int prevweightval, List<Integer> path) {
            this.node = node;
            this.cost = cost;
            this.visitedspecialnodevalue = visitedspecialval;
            this.prevweightvalue = prevweightval;
            this.path = new ArrayList<>(path);
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter number of vertices and edges: format (n m)");
        int n = sc.nextInt();
        int m = sc.nextInt();


        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }


        System.out.println("Enter the edges start stop edge and weight value (format: u v w):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Edge(v, w));
        }


        System.out.println("Enter the number of special vertices example input format (n)");
        int kvalue = sc.nextInt();
        Set<Integer> specialverticesvalue = new HashSet<>();
        System.out.println("Enter the special vertices example input format (n)");
        for (int i = 0; i < kvalue; i++) {
            specialverticesvalue.add(sc.nextInt());
        }

        // Input source and destinations
        System.out.println("Enter source and destination input format (s d)");
        int sourcenodevalue = sc.nextInt();
        int destinationnodevalue = sc.nextInt();

        // run dijkstra's algorithm to find the minimum valid path
        String result = dijkstra(graph, n, specialverticesvalue, sourcenodevalue, destinationnodevalue);

        // Output result
        System.out.println(result);

        sc.close();
    }

    public static String dijkstra(List<Edge>[] graph, int n, Set<Integer> specialverticesvalue, int sourceval, int destinationval) {
        // priority queue to process the nodes with the minimum cost
        PriorityQueue<State> pq = new PriorityQueue<>();
        // declare distance array to store minimum costs
        Map<String, Integer> dist = new HashMap<>();

        // next add the source node to the priority queue
        pq.add(new State(sourceval, 0, false, -1, Arrays.asList(sourceval)));
        dist.put(sourceval + "," + false, 0);

        while (!pq.isEmpty()) {
            State current = pq.poll();


            if (current.node == destinationval && current.visitedspecialnodevalue) {
                return "Path: " + current.path + "\nMin. cost= " + current.cost;
            }


            for (Edge edge : graph[current.node]) {
                boolean newvisitedspecialval = current.visitedspecialnodevalue || specialverticesvalue.contains(edge.destinationvertexvalue);
                int newcostval = current.cost + edge.weightvalue;

                if (current.prevweightvalue == -1 || (0.5 * current.prevweightvalue <= edge.weightvalue && edge.weightvalue <= 2 * current.prevweightvalue)) {
                    String statekeyval = edge.destinationvertexvalue + "," + newvisitedspecialval;
                    if (!dist.containsKey(statekeyval) || dist.get(statekeyval) > newcostval) {
                        dist.put(statekeyval, newcostval);
                        List<Integer> newpathval = new ArrayList<>(current.path);
                        newpathval.add(edge.destinationvertexvalue);
                        pq.add(new State(edge.destinationvertexvalue, newcostval, newvisitedspecialval, edge.weightvalue, newpathval));
                    }
                }
            }
        }

        return "-1";
    }
}
