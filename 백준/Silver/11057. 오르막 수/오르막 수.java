import java.io.*;

public class Main {

    static int N;
    static int count;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // N자리의 숫자중 끝자리가 ?인것
        dp = new int[N + 1][10];
        count = 0;

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= 10007;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            count += dp[N][i];
        }
        System.out.println(count % 10007);
    }
}