import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
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

    static long INF = Long.MAX_VALUE / 3;

    public static void dijkstra(int start) {
        PriorityQueue<Node> pQue = new PriorityQueue<>(Comparator.comparingLong(n -> n.cost));
        dist[start] = 0;
        pQue.add(new Node(start, 0));

        while (!pQue.isEmpty()) {
            Node now = pQue.poll();

            if (dist[now.v] != now.cost)
                continue;

            for (Node next : graph[now.v]) {
                if (now.cost + next.cost < dist[next.v]) {
                    dist[next.v] = now.cost + next.cost;
                    pQue.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

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
            long c = Long.parseLong(stz.nextToken());

            graph[a].add(new Node(b, c));
        }

        stz = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(stz.nextToken());
        int end = Integer.parseInt(stz.nextToken());
        dijkstra(start);
        System.out.println(dist[end]);
    }
}