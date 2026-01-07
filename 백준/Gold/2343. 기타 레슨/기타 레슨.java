import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;

    static boolean isoKay(int X) {
        int total = 0, cnt = 1;
        for (int i = 0; i < N; i++) {
            if (total + arr[i] > X) {
                cnt++;
                total = 0;
            }
            total += arr[i];
            if (cnt > M)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        arr = new int[N];

        stz = new StringTokenizer(br.readLine());
        int left = 0, right = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());

            left = Integer.max(left, arr[i]);
            right += arr[i];
        }

        while (left < right) {
            int mid = (left + right) / 2;

            if (isoKay(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}