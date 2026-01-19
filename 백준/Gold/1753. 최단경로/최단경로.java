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

    static int V, E;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static final int INF = 3_000_005;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        V = Integer.parseInt(stz.nextToken());
        E = Integer.parseInt(stz.nextToken());
        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        dist = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());

            graph[a].add(new Node(b, c));
        }
        dijkstra(start);

        String str;
        for (int i = 1; i <= V; i++) {
            str = (dist[i] == INF ? "INF\n" : String.valueOf(dist[i]) + "\n");
            bw.write(str);
        }
        bw.flush();
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparing(n -> n.cost));
        dist[start] = 0;
        que.offer(new Node(start, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (dist[now.v] != now.cost)
                continue;

            for (Node next : graph[now.v]) {
                if (next.cost + now.cost < dist[next.v]) {
                    dist[next.v] = next.cost + now.cost;
                    que.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}