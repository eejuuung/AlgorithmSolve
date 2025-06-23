import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] parr = new int[10000001];
        int[] marr = new int[10000001];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(stz.nextToken());

            if (num >= 0)
                parr[num]++;
            else {
                num = Math.abs(num);
                marr[num]++;
            }
        }

        int M = Integer.parseInt(br.readLine());
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(stz.nextToken());

            if (num >= 0) {
                sb.append(parr[num]).append(" ");
            } else {
                num = Math.abs(num);
                sb.append(marr[num]).append(" ");
            }
        }

        System.out.println(sb);
    }
}
