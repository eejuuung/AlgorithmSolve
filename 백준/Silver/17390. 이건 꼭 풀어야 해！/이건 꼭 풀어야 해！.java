import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(stz.nextToken());
        int Q = Integer.parseInt(stz.nextToken());
        int[] arr = new int[N + 1];

        stz = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        Arrays.sort(arr);
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }

        for (int i = 0; i < Q; i++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());

            int num = arr[b] - arr[a - 1];
            sb.append(num).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}