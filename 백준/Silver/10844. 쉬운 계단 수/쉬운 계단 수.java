import java.io.*;

public class Main {

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][10];

        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {

                if (j - 1 >= 0)
                    dp[i][j] += dp[i - 1][j - 1];
                if (j + 1 < 10)
                    dp[i][j] += dp[i - 1][j + 1];

                dp[i][j] %= INF;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[N - 1][i];
            answer %= INF;
        }

        System.out.println(answer);

    }
}