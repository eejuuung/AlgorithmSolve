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

    public static ArrayList<Node>[] graph;
    public static int[] dist;
    public static int N, M, X, Y;
    public static final int INF = 100_000_000;

    public static void dijkstra(int start) {
        PriorityQueue<Node> pQue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
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
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        X = Integer.parseInt(stz.nextToken());
        Y = Integer.parseInt(stz.nextToken());
        dist = new int[N];
        graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
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

        dijkstra(Y);
        Arrays.sort(dist);
        if (dist[N - 1] == INF || dist[N - 1] * 2 > X) {
            System.out.println("-1");
            return;
        }

        int days = 1;
        int total = 0;
        for (int i = 0; i < N; i++) {
            total += (dist[i] * 2);

            if (total > X) {
                days++;
                total = dist[i] * 2;
            }

        }
        System.out.println(days);
    }
}