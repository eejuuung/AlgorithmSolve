import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        int dp[] = new int[K + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            for (int j = num; j <= K; j++) {
                dp[j] += dp[j - num];
            }
        }

        System.out.println(dp[K]);
    }
}
