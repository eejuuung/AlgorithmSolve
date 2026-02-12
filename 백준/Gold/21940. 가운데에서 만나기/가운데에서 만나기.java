import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int[][] dist = new int[N][N];
        final int INF = 100_000_000;

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken()) - 1;
            int b = Integer.parseInt(stz.nextToken()) - 1;
            int c = Integer.parseInt(stz.nextToken());
            dist[a][b] = Math.min(dist[a][b], c);
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (dist[i][k] == INF)
                    continue;
                for (int j = 0; j < N; j++) {
                    if (dist[k][j] == INF)
                        continue;
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        int K = Integer.parseInt(br.readLine());
        int[] lands = new int[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int friend = Integer.parseInt(stz.nextToken()) - 1;
            for (int j = 0; j < N; j++) {
                if (lands[j] == INF)
                    continue;
                
                if (dist[j][friend] == INF || dist[friend][j] == INF)
                    lands[j] = INF;
                else
                    lands[j] = Math.max(lands[j], dist[j][friend] + dist[friend][j]);
            }
        }

        int answer = INF;
        for (int i = 0; i < N; i++) {
            answer = (lands[i] != INF ? Math.min(answer, lands[i]) : answer);
        }
        for (int i = 0; i < N; i++) {
            if (lands[i] == answer)
                sb.append(i + 1).append(" ");
        }

        System.out.println(sb);
    }
}
