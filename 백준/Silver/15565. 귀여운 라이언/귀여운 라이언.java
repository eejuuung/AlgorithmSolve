import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        stz = new StringTokenizer(br.readLine());

        int[] memory = new int[N];

        int answer = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0, j = 0, k = 0; i < N; i++) {
            int num = Integer.parseInt(stz.nextToken());

            if (num == 1) {
                count++;
                memory[j++] = i;
            }

            // K개의 라이언이 만족되었다면 더 체크할 필요 없음.
            if (count == K) {
                if (answer > i - memory[k] + 1)
                    answer = i - memory[k] + 1;
                k++;
                count--;
            }

        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);


    }
}