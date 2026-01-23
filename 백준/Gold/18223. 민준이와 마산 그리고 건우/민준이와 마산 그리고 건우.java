import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int v;
        int cost;

        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static final int INF = 5_500_000_00;
    static ArrayList<Node>[] graph;
    static int[] distM;
    static int[] distG;
    static int V;

    public static int[] dijkstra(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pQue = new PriorityQueue<>(Comparator.comparing(n -> n.cost));
        dist[start] = 0;
        pQue.offer(new Node(start, 0));

        while (!pQue.isEmpty()) {
            Node now = pQue.poll();

            if (dist[now.v] != now.cost)
                continue;

            for (Node next : graph[now.v]) {
                if (now.cost + next.cost < dist[next.v]) {
                    dist[next.v] = now.cost + next.cost;
                    pQue.offer(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        V = Integer.parseInt(stz.nextToken());
        int E = Integer.parseInt(stz.nextToken());
        int gunWoo = Integer.parseInt(stz.nextToken());

        graph = new ArrayList[V + 1];
        distM = new int[V + 1];
        distG = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        distM = dijkstra(1);
        distG = dijkstra(gunWoo);

        System.out.println(distM[gunWoo] + distG[V] == distM[V] ? "SAVE HIM" : "GOOD BYE");

    }
}