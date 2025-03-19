import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        long N = Long.parseLong(stz.nextToken());
        long M = Long.parseLong(stz.nextToken());

        long[] arr = new long[(int) N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        long left = 0;
        long right = M * arr[0];
        long mid = 0;
        long total = 0;
        long answer = right;

        while (left <= right) {

            total = 0;
            mid = (left + right) / 2;

            for (int i = 0; i < N; i++) {
                total += mid / arr[i];
            }

            if (total >= M) {
                right = mid - 1;
                if (mid < answer) {
                    answer = mid;
                }
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);


    }
}
