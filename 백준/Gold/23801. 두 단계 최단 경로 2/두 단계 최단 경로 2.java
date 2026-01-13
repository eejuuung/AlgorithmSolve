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


    public static int N, M, X, Z;
    public static ArrayList<Node>[] graph;
    public static Queue<Integer> pQue;
    public static long[] distX;
    public static long[] distZ;
    public static final Long INF =  Long.MAX_VALUE / 4;

    @SuppressWarnings("unchecked")
    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        graph = new ArrayList[N + 1];
        distX = new long[N + 1];
        distZ = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stz.nextToken());
            int v = Integer.parseInt(stz.nextToken());
            long w = Long.parseLong(stz.nextToken());

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        stz = new StringTokenizer(br.readLine());
        X = Integer.parseInt(stz.nextToken());
        Z = Integer.parseInt(stz.nextToken());

        pQue = new ArrayDeque<>();
        int p = Integer.parseInt(br.readLine());
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < p; i++) {
            pQue.offer(Integer.parseInt(stz.nextToken()));
        }
    }

    public static long[] dijkstra(int start) {
        Queue<Node> que = new PriorityQueue<>(Comparator.comparingLong((Node n) -> n.cost));
        long[] dist = new long[N + 1];

        Arrays.fill(dist, INF);
        dist[start] = 0;
        que.add(new Node(start, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.cost != dist[now.v])
                continue;

            for (Node next : graph[now.v]) {
                long totalCost = next.cost + now.cost;
                if (totalCost < dist[next.v]) {
                    dist[next.v] = totalCost;
                    que.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        init();

        distX = dijkstra(X);
        distZ = dijkstra(Z);

        long answer = INF;
        while (!pQue.isEmpty()) {
            int num = pQue.poll();

            answer = Math.min(answer, distX[num] + distZ[num]);
        }

        System.out.println(answer == INF ? "-1" : answer);
    }
}
