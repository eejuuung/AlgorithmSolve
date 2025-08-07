import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        arr = new int[N];

        int left = 0;
        int right = 1000000000;
        int mid = 0;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            left = Math.max(left, arr[i]);
        }

        while (left <= right) {
            mid = (left + right) / 2;

            if (isokay(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean isokay(int money) {
        int nowMoney = money;
        int count = 1;
        for (int i = 0; i < N; i++) {
            if (nowMoney - arr[i] < 0) {
                nowMoney = money;
                count++;
            }
            if (count > M)
                return false;

            nowMoney -= arr[i];
        }
        return true;
    }
}
