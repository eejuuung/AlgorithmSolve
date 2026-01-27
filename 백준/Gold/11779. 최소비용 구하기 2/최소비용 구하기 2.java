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
    public static int[] prev;
    public static final long INF = Long.MAX_VALUE / 3;

    public static void dijkstra(int start, int end) {
        PriorityQueue<Node> pQue = new PriorityQueue<>((n1, n2) -> Long.compare(n1.cost, n2.cost));
        dist[start] = 0;
        pQue.offer(new Node(start, 0));

        while (!pQue.isEmpty()) {
            Node now = pQue.poll();

            if (now.cost != dist[now.v])
                continue;

            for (Node next : graph[now.v]) {
                if (now.cost + next.cost < dist[next.v]) {
                    dist[next.v] = now.cost + next.cost;
                    prev[next.v] = now.v;
                    pQue.offer(new Node(next.v, dist[next.v]));
                }
            }


        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        dist = new long[N + 1];
        prev = new int[N + 1];

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

        dijkstra(start, end);
        sb.append(dist[end]).append("\n");

        List<Integer> path = new ArrayList<>();
        for (int i = end; i != 0; i = prev[i]) {
            path.add(i);
            if (i == start)
                break;
        }
        Collections.reverse(path);
        sb.append(path.size()).append("\n");
        for (int city : path) {
            sb.append(city).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}