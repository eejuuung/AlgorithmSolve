import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int[][] arr = new int[N][M];
        int[][] dp = new int[N][M];
        String str;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
                dp[i][j] = arr[i][j];

                if (arr[i][j] == 1)
                    answer = 1;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (arr[i][j] == 1) {
                    int minN = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    int maxN = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));

                    if (dp[i - 1][j] == 0 || dp[i][j - 1] == 0 || dp[i - 1][j - 1] == 0)
                        dp[i][j] = 1;
                    else if (maxN - minN == 1)
                        dp[i][j] = maxN;
                    else
                        dp[i][j] = minN + 1;

                    if (dp[i][j] > answer)
                        answer = dp[i][j];

                }
            }
        }

        System.out.println(answer * answer);
    }
}
