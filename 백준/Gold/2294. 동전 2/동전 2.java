import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 100_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int[] dp = new int[M + 1];
        List<Integer> coins = new ArrayList<>();

        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            coins.add(num);
        }
        Collections.sort(coins);

        for (int coin : coins) {
            for (int i = coin; i <= M; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        System.out.println((dp[M] == INF ? -1 : dp[M]));
    }
}
