import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1_000_000_000;

    public static class Node {
        int v;  // 간선
        int cost; // 가중치

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static int N, M;
    public static ArrayList<Node>[] graph;
    public static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int rtc = 0; rtc < tc; rtc++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            M = Integer.parseInt(stz.nextToken());
            graph = new ArrayList[N + 1];
            int[] total = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            int a, b, cost;
            for (int i = 0; i < M; i++) {
                stz = new StringTokenizer(br.readLine());
                a = Integer.parseInt(stz.nextToken());
                b = Integer.parseInt(stz.nextToken());
                cost = Integer.parseInt(stz.nextToken());

                graph[a].add(new Node(b, cost));
                graph[b].add(new Node(a, cost));
            }

            int k = Integer.parseInt(br.readLine());
            stz = new StringTokenizer(br.readLine());

            for (int i = 0; i < k; i++) {
                int start = Integer.parseInt(stz.nextToken());
                dijkstra(start);
                for (int j = 1; j <= N; j++) {
                    total[j] += dist[j];
                }
            }

            int v = 1;
            for (int i = 1; i <= N; i++) {
                if (total[v] > total[i])
                    v = i;
            }

            sb.append(v).append("\n");
        }

        System.out.print(sb);
    }

    public static void dijkstra(int start) {
        boolean[] visit = new boolean[N + 1];
        dist = new int[N + 1];

        PriorityQueue<Node> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        que.add(new Node(start, 0));

        for (int i = 0; i <= N; i++) {
            dist[i] = INF;
        }
        dist[start] = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (visit[now.v]) continue;
            visit[now.v] = true;

            for (Node next : graph[now.v]) {
                if (!visit[next.v] && dist[next.v] > dist[now.v] + next.cost) {
                    dist[next.v] = now.cost + next.cost;
                    que.add(new Node(next.v, dist[next.v]));
                }
            }

        }
    }
}
