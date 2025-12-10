import java.io.*;
import java.util.*;

/*
    N개의 소세지를 이어붙였을때 M개의 조각으로 나누어져야함. (M명의 사람)
    이때, 이어붙인 소세지의 경계점과 잘라야하는 위치가 겹치는 구간의 갯수가 gcd(N,M) - 시작점0포함
    = 즉, 자르지 않아도 되는 횟수
    그러므로 전체 필요한 칼질 수 = (M - 1) - (gcd(N, M) - 1) = M - gcd(N, M)
 */
public class Main {

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        System.out.println(M - gcd(N, M));
    }
}
