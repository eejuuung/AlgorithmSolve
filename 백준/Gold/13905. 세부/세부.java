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

    public static int N, M;
    public static ArrayList<Node>[] graph;
    public static long[] dist;

    public static void dijkstra(int start) {
        Queue<Node> que = new PriorityQueue<>(Comparator.comparing((Node n) -> n.cost).reversed());
        dist[start] = Integer.MAX_VALUE;
        que.add(new Node(start, dist[start]));

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.cost < dist[now.v]) // dist에 지금 cost가 아닌 이전 cost가 들어있다면 지우기
                continue;

            for (Node next : graph[now.v]) {
                long bottleneck = Math.min(dist[now.v], next.cost);

                if (dist[next.v] < bottleneck) {
                    dist[next.v] = bottleneck;
                    que.add(new Node(next.v, dist[next.v]));
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
        stz = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(stz.nextToken());
        int E = Integer.parseInt(stz.nextToken());
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

        dijkstra(S);

        System.out.println(dist[E]);

    }
}