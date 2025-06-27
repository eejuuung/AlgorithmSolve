import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        long K = Long.parseLong(stz.nextToken());
        long[] sum = new long[N + 1];
        long cnt = 0;
        Map<Long, Long> map = new HashMap<>();// [누적합 값, 누적합 등장 횟수]

        stz = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] = Long.parseLong(stz.nextToken());
            sum[i] += sum[i - 1];

            if (sum[i] == K)
                cnt++;
        }

        for (int i = 1; i <= N; i++) {
            cnt += map.getOrDefault(sum[i] - K, 0L);
            map.put(sum[i], map.getOrDefault(sum[i], 0L) + 1);
        }

        System.out.println(cnt);
    }
}
