import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());
        arr = new int[N];

        stz = new StringTokenizer(br.readLine());
        int left = 0;
        int kCount = 0, count = 0, answer = -1;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
            if (arr[i] % 2 == 0 && answer < 0) {
                answer = 0;
                left = i;
            }
        }

        for (int right = left; right < N; right++) {

            if (arr[right] % 2 == 0) {
                count++;
            } else {
                kCount++;
            }

            if (kCount > K) {
                while (left < right) {
                    if (arr[left] % 2 != 0) {
                        left++;
                        kCount--;
                        break;
                    }
                    left++;
                    count--;
                }
            }


            answer = Math.max(answer, count);
        }


        System.out.println(answer);
    }

}