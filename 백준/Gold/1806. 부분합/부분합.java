import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        long S = Long.parseLong(stz.nextToken());

        long[] map = new long[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Long.parseLong(stz.nextToken());

            if (map[i] == S) {
                System.out.println("1");
                return;
            }
        }

        int answer = Integer.MAX_VALUE;
        long total = 0;
        int cnt = 0;
        for (int i = 0, j = 0; j < N; j++) {
            total += map[j];
            cnt++;

            if (total >= S) {
                int k = i;
                while (k < j) {
                    if ((total - map[k]) >= S) {
                        total -= map[k++];
                        cnt--;
                    } else {
                        break;
                    }
                }
                i = k;

                if (cnt < answer) {
                    answer = cnt;
                }
            }
        }

        if (answer == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(answer);

    }
}