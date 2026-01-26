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

    static int N;
    static final long INF = Long.MAX_VALUE / 3;
    static ArrayList<Node>[] graph;
    static long[] dist1;
    static long[] dist2;
    static long[] dist3;

    static long[] dijkstra(int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pQue = new PriorityQueue<>(Comparator.comparingLong(n->n.cost));
        pQue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pQue.isEmpty()) {
            Node now = pQue.poll();
            if (now.cost != dist[now.v])
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
        N = Integer.parseInt(stz.nextToken());
        int E = Integer.parseInt(stz.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            long c = Long.parseLong(stz.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        stz = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(stz.nextToken());
        int v2 = Integer.parseInt(stz.nextToken());

        dist1 = dijkstra(1);
        dist2 = dijkstra(v1);
        dist3 = dijkstra(v2);

        long p1 = (dist1[v1] >= INF || dist2[v2] >= INF || dist3[N] >= INF) ? INF : dist1[v1] + dist2[v2] + dist3[N];
        long p2 = (dist1[v2] >= INF || dist3[v1] >= INF || dist2[N] >= INF) ? INF : dist1[v2] + dist3[v1] + dist2[N];
        long answer = Math.min(p1, p2);

        System.out.println(answer >= INF ? "-1" : answer);
    }
}