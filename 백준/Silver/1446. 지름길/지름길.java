import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int v;
        int cost;

        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static int N, D;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static final int INF = 1_000_000_000;

    static void dijkstra(int start) {
        PriorityQueue<Node> pQue = new PriorityQueue<>(Comparator.comparing(n -> n.cost));
        dist[start] = 0;
        pQue.add(new Node(start, 0));

        while (!pQue.isEmpty()) {
            Node now = pQue.poll();

            if (now.cost != dist[now.v])
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
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        D = Integer.parseInt(stz.nextToken());
        graph = new ArrayList[D + 1];
        dist = new int[D + 1];

        for (int i = 0; i <= D; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
            if (i < D)
                graph[i].add(new Node(i + 1, 1));
        }

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());

            if (a <= D && b <= D && b > a && c < b - a)
                graph[a].add(new Node(b, c));
        }

        dijkstra(0);

        System.out.println(dist[D]);
    }
}