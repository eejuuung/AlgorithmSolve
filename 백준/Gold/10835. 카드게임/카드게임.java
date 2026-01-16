import java.io.*;
import java.util.*;

public class Main {

    static final int INF = -1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++)
            Arrays.fill(dp[i], INF);
        int[] left = new int[N];
        int[] right = new int[N];
        StringTokenizer stzL = new StringTokenizer(br.readLine());
        StringTokenizer stzR = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            left[i] = Integer.parseInt(stzL.nextToken());
            right[i] = Integer.parseInt(stzR.nextToken());
        }

        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == INF)
                    continue;

                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
                
                if (left[i] > right[j]) {
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + right[j]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= N; j++)
                answer = Math.max(answer, dp[i][j]);
        System.out.println(answer);
    }
}
