import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        int[] arr = new int[1000005];
        int maxX = 0;

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(stz.nextToken());
            int x = Integer.parseInt(stz.nextToken());
            maxX = Math.max(x, maxX);

            arr[x] = g;
        }

        int maxValue = 0;
        int totalValue = 0;

        int num = Math.min(K * 2, maxX);
        for (int i = 0; i <= num; i++) {
            totalValue += arr[i];
        }
        maxValue = totalValue;

        for (int j = 0, k = num + 1; k <= maxX; k++, j++) {
            totalValue -= arr[j];
            totalValue += arr[k];

            maxValue = Math.max(maxValue, totalValue);
        }
        System.out.println(maxValue);
    }
}
