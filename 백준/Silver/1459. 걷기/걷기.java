import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static long calDistance(long x, long y, long w, long s) {

        long calValue = 0;


        return calValue;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        long x = Long.parseLong(stz.nextToken());
        long y = Long.parseLong(stz.nextToken());
        //선
        long w = Long.parseLong(stz.nextToken());
        //대각선
        long s = Long.parseLong(stz.nextToken());

        // 1. 평행이동시
        long minValue = (x + y) * w;

        long val;
        if ((x + y) % 2 == 0) {
            // 2. 대각선으로만 이동시 두 좌표합 짝수인경우
            val = Math.max(x, y) * s;
        } else {
            // 3. 대각선으로만 이동시 두 좌표합 홀수인경우
            val = (Math.max(x, y) - 1) * s + w;
        }
        minValue = Math.min(minValue, val);

        // 4. 대각선으로 최소만큼 가고 나머지 선이동
        val = (Math.min(x, y)) * s + (Math.abs(x - y)) * w;
        minValue = Math.min(minValue, val);

        System.out.println(minValue);

    }
}