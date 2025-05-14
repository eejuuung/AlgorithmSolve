import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        int num = 10;
        int count = 1;
        int leng = 0;
        int answer = -1;

        for (int i = 1; i <= N; i++) {
            if (num == i) {
                num *= 10;
                count++;
            }

            if (leng + count >= K) {
                String str = String.valueOf(i);
                int strI = count + (K - (leng + count)) - 1;
                answer = str.charAt(strI) - '0';
                break;
            } else {
                leng += count;
            }
        }

        System.out.println(answer);

    }
}
