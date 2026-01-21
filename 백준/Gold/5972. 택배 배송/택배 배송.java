import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int v;
        long cost;

        Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static int N, M;
    static ArrayList<Node>[] graph;
    static long[] dist;

    static final long INF = Long.MAX_VALUE / 3;

    static void dijkstra(int start) {
        PriorityQueue<Node> pQue = new PriorityQueue<>(Comparator.comparingLong((Node n) -> n.cost));
        dist[start] = 0;
        pQue.add(new Node(start, 0));

        while (!pQue.isEmpty()) {
            Node now = pQue.poll();

            if (now.cost != dist[now.v])
                continue;

            for (Node next : graph[now.v]) {
                if (next.cost + now.cost < dist[next.v]) {
                    dist[next.v] = next.cost + now.cost;
                    pQue.add(new Node(next.v, dist[next.v]));
                }

            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        graph = new ArrayList[N + 1];
        dist = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        dijkstra(1);

        System.out.println(dist[N]);

    }
}