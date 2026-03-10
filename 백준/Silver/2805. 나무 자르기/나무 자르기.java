import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long M;
    static long[] arr;

    static boolean isOkay(long height) {

        long total = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > height)
                total += (arr[i] - height);
        }
        return total >= M;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        stz = new StringTokenizer(br.readLine());
        arr = new long[N];
        long left = 0, right = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(stz.nextToken());
            right = Math.max(right, arr[i]);
        }

        long answer = 0;
        while (left < right) {
            long mid = (left + right) / 2;

            if (isOkay(mid)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid;
            }
        }

        System.out.println(answer);

    }
}