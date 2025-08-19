import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int B = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        boolean[] signal = new boolean[N + 1];
        for (int i = 1; i <= K; i++) {
            int num = Integer.parseInt(br.readLine());
            signal[num] = true;
        }

        int left = 1, right = 1;
        int brokenCount = 0;
        for (; right <= B; right++) {
            if (signal[right])
                brokenCount++;
        }

        int answer = brokenCount;

        for (; right <= N; left++, right++) {
            if (signal[left])
                brokenCount--;

            if (signal[right])
                brokenCount++;

            answer = Math.min(answer, brokenCount);
        }

        System.out.println(answer);


    }
}
