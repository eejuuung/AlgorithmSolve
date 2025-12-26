import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] child;

    // 올림나눗셈 공식 (a+b-1)/b
    static boolean isOkay(int min) {
        int total = 0;
        for (int i = 0; i < M; i++) {
            total += (child[i] + min - 1) / min;
        }

        return (total <= N);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        child = new int[M];
        int maxd = 0;

        for (int i = 0; i < M; i++) {
            child[i] = Integer.parseInt(br.readLine());
            maxd = Math.max(maxd, child[i]);
        }

        int left = 1;
        int right = maxd;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;

            if (!isOkay(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }
}