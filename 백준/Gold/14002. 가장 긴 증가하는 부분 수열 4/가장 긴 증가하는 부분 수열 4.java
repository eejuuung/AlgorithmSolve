import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int size;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        size = 1;
        int num = 0;

        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {

                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else if (arr[i] == arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                } else {
                    dp[i] = Math.max(dp[i], 1);
                }
            }

            size = Math.max(size, dp[i]);
            if (size == dp[i])
                num = i;
        }


        StringBuilder sb = new StringBuilder();
        Stack<Integer> sta = new Stack<>();

        sb.append(size).append("\n");

        for (int i = num; i >= 0; i--) {
            if (dp[i] == size) {
                sta.add(arr[i]);
                size--;
            }
        }
        while (!sta.isEmpty()) {
            sb.append(sta.pop()).append(" ");
        }

        System.out.println(sb);

    }
}
