import java.io.*;
import java.util.*;

public class Main {

    public static int gcd(int a, int b) {

        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(stz.nextToken());
        int N = Integer.parseInt(stz.nextToken());
        if (M == 0 && N == 0) {
            System.out.println(0);
            return;
        }

        int g = gcd(Math.abs(M), Math.abs(N));

        if (g == 1) System.out.println(1);
        else System.out.println(2);
    }
}