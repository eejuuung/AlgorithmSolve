import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        boolean[] bmap = new boolean[N + 1];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        long totalCount = 0;
        int j = 0;
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            for (; j < N; j++) {
                if (bmap[arr[j]]) {
                    break;
                } else {
                    cnt++;
                    bmap[arr[j]] = true;
                }
            }
            bmap[arr[i]] = false;
            totalCount += cnt;
            cnt--;

        }

        System.out.println(totalCount);

    }
}