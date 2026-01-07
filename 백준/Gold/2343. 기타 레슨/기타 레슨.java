import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;

    static boolean isoKay(int X) {
        int total = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                if (arr[j] - arr[i - 1] > X) {
                    i = j - 1;
                    total++;
                    break;
                }
            }
            if (total + 1 > M)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        arr = new int[N + 1];
        int minX = 0, maxX = 0;

        stz = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(stz.nextToken());

            arr[i] = arr[i - 1] + num;
            minX = Integer.max(minX, num);
        }
        maxX = arr[N];

        int left = minX, right = arr[N], mid;
        while (left < right) {
            mid = (left + right) / 2;

            if (isoKay(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}