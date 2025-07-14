import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        int num, val;
        long totalValue = 0L;
        int xorValue = 0;

        for (int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            num = Integer.parseInt(stz.nextToken());

            switch (num) {
                case 1:
                    val = Integer.parseInt(stz.nextToken());
                    totalValue += (long) val;
                    xorValue ^= val;
                    break;
                case 2:
                    val = Integer.parseInt(stz.nextToken());
                    totalValue -= (long) val;
                    xorValue ^= val;
                    break;
                case 3:
                    sb.append(totalValue).append("\n");
                    break;
                case 4:
                    sb.append(xorValue).append("\n");

                    break;
            }
        }

        bw.write(sb.toString());
        bw.flush();

    }
}
