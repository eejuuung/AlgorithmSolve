import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dist;
    static boolean[] visit;
    static int answer;

    public static void dfs(int now, int time, int count) {

        if (count >= N) {
            answer = Math.min(answer, time);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(i, time + dist[now][i], count + 1);
                visit[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        dist = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        answer = Integer.MAX_VALUE;
        visit[K] = true;
        dfs(K, 0, 1);

        System.out.println(answer);

    }
}