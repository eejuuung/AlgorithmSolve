import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];
        int maxD = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        for (int i = N; i > 0; i--) {

            for (int j = N; j > i; j--) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }

            if (dp[i] > maxD)
                maxD = dp[i];
        }

        System.out.println(maxD + 1);

    }

}
