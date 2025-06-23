import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        boolean[] parr = new boolean[10000001];
        boolean[] marr = new boolean[10000001];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(stz.nextToken());

            if (num >= 0)
                parr[num] = true;
            else {
                num = Math.abs(num);
                marr[num] = true;
            }
        }

        int M = Integer.parseInt(br.readLine());
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(stz.nextToken());

            if (num >= 0) {
                if (parr[num])
                    sb.append("1 ");
                else
                    sb.append("0 ");
            } else {
                num = Math.abs(num);
                if (marr[num])
                    sb.append("1 ");
                else
                    sb.append("0 ");
            }
        }

        System.out.println(sb);
    }
}
