import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr, Comparator.reverseOrder());

        int answer = 0;
        int i = 0;
        while (K != 0) {
            if (K >= arr[i]) {
                int count = K / arr[i];
                K -= (arr[i] * count);
                answer += count;
            }
            i++;
        }
        System.out.println(answer);
    }
}