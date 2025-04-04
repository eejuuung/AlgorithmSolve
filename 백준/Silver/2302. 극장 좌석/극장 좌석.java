import java.io.*;

public class Main {

    static int N, M;
    static int[] dp;
    static int count;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        count = 1;

        // dp[n] = dp[n-1] + dp[n-2];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int tmp = 0;
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            count *= dp[num - tmp - 1];
            tmp = num;
        }

        count *= dp[N - tmp];

        System.out.println(count);

    }
}
