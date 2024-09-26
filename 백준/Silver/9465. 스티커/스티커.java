import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int rtc = 0; rtc < tc; rtc++) {
            int N = Integer.parseInt(br.readLine());

            int[][] dp = new int[2][N + 1];
            int[][] map = new int[2][N + 1];
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[0][j] = Integer.parseInt(stz.nextToken());
            }

            stz = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[1][j] = Integer.parseInt(stz.nextToken());
            }

            dp[0][1] = map[0][1];
            dp[1][1] = map[1][1];

            for (int i = 2; i <= N; i++) {
                dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + map[0][i];
                dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + map[1][i];
            }
            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }
        System.out.print(sb);
    }
}