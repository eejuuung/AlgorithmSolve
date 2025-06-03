import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[M];

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            map[i] = Integer.parseInt(stz.nextToken());
        }
        Arrays.sort(map);

        int left = 0;
        int right = N;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);

    }

    public static boolean check(int h) {
        int prev = 0; // 가로등이 비춘 마지막 지점.

        for (int i = 0; i < M; i++) {
            if (map[i] - h <= prev) {
                prev = map[i] + h;
            } else {
                return false;
            }
        }

        return N - prev <= 0;
    }

}
