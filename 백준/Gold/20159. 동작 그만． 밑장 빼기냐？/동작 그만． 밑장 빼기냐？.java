import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int leng = N / 2;
        int[] evenNumber = new int[leng];
        int[] oddNumber = new int[leng];

        int even = Integer.parseInt(stz.nextToken());
        int odd = Integer.parseInt(stz.nextToken());
        evenNumber[0] = even;
        oddNumber[0] = odd;

        for (int i = 1; i < leng; i++) {
            even = Integer.parseInt(stz.nextToken());
            odd = Integer.parseInt(stz.nextToken());
            evenNumber[i] = evenNumber[i - 1] + even;
            oddNumber[i] = oddNumber[i - 1] + odd;
        }

        int answer = Math.max(evenNumber[leng - 1], oddNumber[leng - 1]);
        for (int i = 0; i < leng; i++) {
            // 내 차례에 밑장뺀경우
            odd = oddNumber[leng - 1] - oddNumber[i];
            even = evenNumber[i] + odd;
            if (answer < (even))
                answer = even;

            // 상대방 차례에 밑장뺀경우
            if (leng > 1) {
                int num = oddNumber[leng - 1] - oddNumber[leng - 2];
                if (i > 0)
                    even = (evenNumber[i]) + (oddNumber[leng - 1] - oddNumber[i - 1]) - num;
                else
                    even = (evenNumber[i]) + (oddNumber[leng - 1]) - num;

                if (answer < (even))
                    answer = even;
            }
        }

        System.out.println(answer);
    }
}
