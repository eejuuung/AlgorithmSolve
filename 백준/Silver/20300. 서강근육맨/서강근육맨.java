import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());
        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(stz.nextToken());
        }
        Arrays.sort(arr);
        boolean odd = N % 2 == 1;

        long answer = 0;
        if (odd) {
            answer = arr[--N];
        }
        for (int i = 0, j = N - 1; j > i; i++, j--) {
            answer = Math.max(answer, arr[i] + arr[j]);
        }

        System.out.println(answer);
    }
}