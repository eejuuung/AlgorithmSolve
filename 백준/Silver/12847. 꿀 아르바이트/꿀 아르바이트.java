import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        long[] sliding = new long[N + 1];
        long answer = 0;

        stz = new StringTokenizer(br.readLine());
        for (int i = 1, j = 0; i <= N; i++) {
            sliding[i] = sliding[i - 1] + Long.parseLong(stz.nextToken());
            if (i >= M) {
                answer = Math.max(answer, sliding[i] - sliding[j++]);
            }
        }
        System.out.println(answer);
    }
}
