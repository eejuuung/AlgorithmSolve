import java.io.*;
import java.util.*;

public class Main {

    public static int gcd(int a, int b) {

        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int R = Integer.parseInt(stz.nextToken());
        int G = Integer.parseInt(stz.nextToken());
        int num = gcd(R, G);

        for (int i = 1; i <= num; i++) {
            if (R % i == 0 && G % i == 0)
                sb.append(i).append(" ").append(R / i).append(" ").append(G / i).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

    }
}