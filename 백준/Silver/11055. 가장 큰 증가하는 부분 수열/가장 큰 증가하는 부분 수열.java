import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int total = 1001;
        int[] arr = new int[total];
        int[] dp = new int[total];
        int maxD = 0;

        for (int i = 1; i <= N; i++) {

            arr[i] = Integer.parseInt(stz.nextToken());
            dp[i] = arr[i];

            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + arr[i]) {
                    dp[i] = dp[j] + arr[i];
                }
            }

            if (maxD < dp[i])
                maxD = dp[i];
        }

        System.out.println(maxD);

    }
}
