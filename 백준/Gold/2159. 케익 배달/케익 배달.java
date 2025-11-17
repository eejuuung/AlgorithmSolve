import java.io.*;
import java.util.*;

// dp[i][k] = i번째까지 배달했을때 k위치의 이동값
// k = 0~5 -> 원상태, 오, 아, 왼, 위
public class Main {

    static long dy[] = {0, 0, 1, 0, -1};
    static long dx[] = {0, 1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][5];
        long[][] prevPos = new long[5][2];
        long[][] nowPos = new long[5][2];

        StringTokenizer stz = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(stz.nextToken());
        int x = Integer.parseInt(stz.nextToken());

        for (int i = 0; i < 5; i++) {
            prevPos[i][0] = y + dy[i];
            prevPos[i][1] = x + dx[i];
        }

        for (int i = 0; i < 5; i++)
            dp[0][i] = Math.abs(prevPos[i][0] - y) + Math.abs(prevPos[i][1] - x);


        for (int i = 1; i <= N; i++) {
            stz = new StringTokenizer(br.readLine());
            int py = Integer.parseInt(stz.nextToken());
            int px = Integer.parseInt(stz.nextToken());

            for (int j = 0; j < 5; j++) {
                nowPos[j][0] = py + dy[j];
                nowPos[j][1] = px + dx[j];
                dp[i][j] = Long.MAX_VALUE;
            }

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    long dis = Math.abs(prevPos[k][0] - nowPos[j][0]) +
                            Math.abs(prevPos[k][1] - nowPos[j][1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + dis);
                }
            }

            for (int j = 0; j < 5; j++) {
                prevPos[j][0] = nowPos[j][0];
                prevPos[j][1] = nowPos[j][1];
            }
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++)
            ans = Math.min(ans, dp[N][i]);

        System.out.println(ans);
    }
}
