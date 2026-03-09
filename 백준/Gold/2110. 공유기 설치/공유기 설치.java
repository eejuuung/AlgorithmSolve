import java.io.*;
import java.util.*;

public class Main {

    static int N, C;
    static int[] arr;

    public static boolean isOkay(int dist) {
        int count = 1;
        int last = arr[0];

        for (int i = 1; i < N; i++) {
            if (arr[i] - last >= dist) {
                count++;
                last = arr[i];
            }
        }

        return count >= C;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = arr[N - 1] - arr[0];
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isOkay(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}