import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int rtc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < rtc; tc++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int[] arr = new int[a];
            int[] brr = new int[b];

            stz = new StringTokenizer(br.readLine());
            for (int i = 0; i < a; i++) {
                arr[i] = Integer.parseInt(stz.nextToken());
            }
            Arrays.sort(arr);

            stz = new StringTokenizer(br.readLine());
            for (int i = 0; i < b; i++) {
                brr[i] = Integer.parseInt(stz.nextToken());
            }
            Arrays.sort(brr);

            int count = 0;

            for (int i = 0, j = 0; i < a; i++) {
                if (arr[i] > brr[j]) {
                    while (j + 1 < b && brr[j + 1] < arr[i])
                        j++;

                    count += (j + 1);
                }
            }

            sb.append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
