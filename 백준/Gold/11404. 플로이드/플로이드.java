import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] dist = new int[N][N];
        final int INF = 100_000_000;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken()) - 1;
            int b = Integer.parseInt(stz.nextToken()) - 1;
            int cost = Integer.parseInt(stz.nextToken());

            dist[a][b] = Math.min(dist[a][b], cost);
        }


        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (dist[i][k] == INF)
                    continue;
                for (int j = 0; j < N; j++) {
                    if (dist[k][j] == INF)
                        continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}