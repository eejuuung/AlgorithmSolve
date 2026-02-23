import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N, M;
    static final int INF = 100_000_000;
    static int[][] dist;
    static int[][] map;
    // 오, 아, 왼, 위
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void bfs01() {
        Deque<Node> que = new ArrayDeque<>();
        que.offerFirst(new Node(0, 0));
        dist[0][0] = 0;

        while (!que.isEmpty()) {
            Node now = que.pollFirst();
            for (int i = 0; i < 4; i++) {
                int fy = now.y + dy[i];
                int fx = now.x + dx[i];
                if (fy < 0 || fx < 0 || fy >= N || fx >= M)
                    continue;

                int cost = dist[now.y][now.x] + map[fy][fx];
                if (cost < dist[fy][fx]) {
                    dist[fy][fx] = cost;

                    if (map[fy][fx] == 0)
                        que.offerFirst(new Node(fy, fx));
                    else
                        que.offerLast(new Node(fy, fx));
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        M = Integer.parseInt(stz.nextToken());
        N = Integer.parseInt(stz.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            Arrays.fill(dist[i], INF);
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs01();

        System.out.println(dist[N - 1][M - 1]);
    }
}
