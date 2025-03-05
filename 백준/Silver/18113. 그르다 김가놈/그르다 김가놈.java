import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int num;

        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());

            if (num <= K) {
                continue;
            } else if (num < (2 * K)) {
                num -= K;
            } else {
                num -= (2 * K);
            }

            if (num > 0) {
                list.add(num);
            }
        }

        int l = 1;
        int h = 1000000000;
        int result = -1;

        while (l <= h) {
            int mid = (l + h) / 2;
            int cnt = 0;

            for (int i = 0; i < list.size(); i++) {
                cnt += list.get(i) / mid;
            }

            if (cnt < M) {
                h = mid - 1;
            } else {
                result = mid;
                l = mid + 1;
            }

        }

        System.out.println(result);
    }
}
