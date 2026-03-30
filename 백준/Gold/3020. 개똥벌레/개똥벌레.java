import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int H = Integer.parseInt(stz.nextToken());
        int[] suksun = new int[H + 1];
        int[] jongyusuk = new int[H + 1];

        int num = Integer.parseInt(br.readLine());
        suksun[num]++;
        for (int i = 1; i < N; i++) {
            num = Integer.parseInt(br.readLine());

            if (i % 2 == 0)
                suksun[num]++;
            else
                jongyusuk[H - num + 1]++;
        }

        for (int i = 2, j = H - 1; i <= H; i++, j--) {
            suksun[j] += suksun[j + 1];
            jongyusuk[i] += jongyusuk[i - 1];
        }

        num = 0;
        int total = Integer.MAX_VALUE;
        int[] totalArr = new int[N + 1];
        for (int i = 1; i <= H; i++) {
            num = suksun[i] + jongyusuk[i];
            totalArr[num]++;
            if (num < total) {
                total = suksun[i] + jongyusuk[i];
            }
        }
        sb.append(total).append(" ").append(totalArr[total]).append("\n");
        System.out.print(sb);
    }
}