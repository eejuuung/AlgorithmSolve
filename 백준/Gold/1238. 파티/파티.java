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

    public static ArrayList<Node>[] forwardG;
    public static ArrayList<Node>[] reverseG;
    public static int[] forwardD;
    public static int[] reverseD;
    public static int N, M, X;

    public static final int INF = 100_000_000;

    public static void dijkstra(int start, ArrayList<Node>[] graph, int[] dist) {
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        que.offer(new Node(start, 0));
        dist[start] = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (dist[now.v] != now.cost)
                continue;

            for (Node next : graph[now.v]) {
                if (dist[next.v] > next.cost + now.cost) {
                    dist[next.v] = next.cost + now.cost;
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
        X = Integer.parseInt(stz.nextToken()) - 1;
        forwardG = new ArrayList[N];
        reverseG = new ArrayList[N];
        forwardD = new int[N];
        reverseD = new int[N];

        for (int i = 0; i < N; i++) {
            forwardG[i] = new ArrayList<>();
            reverseG[i] = new ArrayList<>();
            forwardD[i] = INF;
            reverseD[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken()) - 1;
            int b = Integer.parseInt(stz.nextToken()) - 1;
            int c = Integer.parseInt(stz.nextToken());
            forwardG[a].add(new Node(b, c));
            reverseG[b].add(new Node(a, c));
        }

        int answer = 0;
        dijkstra(X, forwardG, forwardD);
        dijkstra(X, reverseG, reverseD);

        for (int i = 0; i < N; i++) {
            if (forwardD[i] + reverseD[i] >= INF)
                continue;
            answer = Math.max(answer, forwardD[i] + reverseD[i]);
        }

        System.out.println(answer);

    }
}