import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int v;
        long cost;

        public Node(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static ArrayList<Node>[] graph;
    static long[] dist;
    static int N, M;
    static int sIsland, eIsland;

    public static void dijkstra(int start) {
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingLong((Node o) -> o.cost).reversed());

        dist[start] = Long.MAX_VALUE;
        que.add(new Node(start, dist[start]));

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.cost < dist[now.v]) // 최신값이 아닌 이전값 스킵
                continue;

            for (Node next : graph[now.v]) {
                long bottleneck = Math.min(dist[now.v], next.cost); // 이전까지의 이동값 최소 중량으로 선택
                if (dist[next.v] < bottleneck) {
                    dist[next.v] = bottleneck;
                    que.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        graph = new ArrayList[N + 1];
        dist = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            long c = Long.parseLong(stz.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        stz = new StringTokenizer(br.readLine());
        sIsland = Integer.parseInt(stz.nextToken());
        eIsland = Integer.parseInt(stz.nextToken());

        dijkstra(sIsland);

        System.out.println(dist[eIsland]);
    }
}