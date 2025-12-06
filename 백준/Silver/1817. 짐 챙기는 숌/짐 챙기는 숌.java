import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        int beforebox = 0;
        int answer = 0;

        if (N != 0)
            stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(stz.nextToken());

            if (beforebox - num >= 0) {
                beforebox -= num;
            } else {
                answer++;
                beforebox = M - num;
            }
        }

        System.out.println(answer);
    }
}
