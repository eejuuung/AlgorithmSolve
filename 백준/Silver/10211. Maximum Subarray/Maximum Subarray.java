import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int[] X = new int[N + 1];
            int answer = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                int num = Integer.parseInt(stz.nextToken());
                X[i] = X[i - 1] + num;

                for (int j = 0; j < i; j++) {
                    answer = Math.max(answer, X[i] - X[j]);
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}