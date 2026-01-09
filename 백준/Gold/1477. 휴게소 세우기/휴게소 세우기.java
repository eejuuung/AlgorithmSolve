import java.io.*;
import java.util.*;

public class Main {

    public static int N, M, L;
    public static int[] load;

    public static boolean isOkay(int gap) {
        int total = 0;
        for (int i = 0; i <= N; i++) {
            total += ((load[i] - 1) / gap);
        }
        return total <= M;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        L = Integer.parseInt(stz.nextToken());
        int[] arr = new int[N + 1];
        load = new int[N + 1];
        int left = 1, right = 0, mid = 0;

        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        arr[N] = L;
        Arrays.sort(arr);
        for (int i = 0, beforeNum = 0; i <= N; i++) {
            load[i] = arr[i] - beforeNum;
            beforeNum = arr[i];
            right = Math.max(right, load[i]);
        }

        while (left < right) {
            mid = (left + right) / 2;

            if (isOkay(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}