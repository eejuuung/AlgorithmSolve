import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 100_000_000;
    public static int N, M;
    public static int[][] dist;
    public static int[] items;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        int R = Integer.parseInt(stz.nextToken());
        dist = new int[N][N];
        items = new int[N];

        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(stz.nextToken());
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < R; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken()) - 1;
            int b = Integer.parseInt(stz.nextToken()) - 1;
            int c = Integer.parseInt(stz.nextToken());

            dist[a][b] = Math.min(dist[a][b], c);
            dist[b][a] = Math.min(dist[b][a], c);
        }

        // 플로이드워셜
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (dist[i][k] == INF || dist[i][k] > M)
                    continue;

                for (int j = 0; j < N; j++) {
                    if (dist[k][j] == INF || dist[k][j] > M)
                        continue;

                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int total = 0;
            for (int j = 0; j < N; j++) {
                if (dist[i][j] <= M) {
                    total += items[j];
                }
            }
            answer = Math.max(answer, total);
        }

        System.out.println(answer);
    }
}