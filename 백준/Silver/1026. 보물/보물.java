import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] brr = new int[N];

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            brr[i] = Integer.parseInt(stz.nextToken());
        }
        Arrays.sort(arr);
        Arrays.sort(brr);

        int S = 0;
        for (int i = 0, j = N - 1; i < N; i++, j--) {
            S += (arr[i] * brr[j]);
        }

        System.out.println(S);
    }
}
