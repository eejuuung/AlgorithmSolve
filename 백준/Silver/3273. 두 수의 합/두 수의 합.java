import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        Arrays.sort(arr);

        int i = 0;
        int j = N - 1;
        int count = 0;

        while (i < j) {
            int num = arr[i] + arr[j];

            if (num == X) {
                count++;
                i++;
            } else if (num > X) {
                j--;
            } else {
                i++;
            }
        }

        System.out.println(count);
    }
}
