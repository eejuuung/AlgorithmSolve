import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer stz = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pQue = new PriorityQueue<>();
            for (int i = 0; i < K; i++) {
                pQue.offer(Long.parseLong(stz.nextToken()));
            }

            long total = 0;
            while (!pQue.isEmpty() && pQue.size() > 1) {
                long c1 = pQue.poll();
                long c2 = pQue.poll();
                long c3 = c1 + c2;
                total += c3;
                pQue.offer(c3);
            }
            sb.append(total).append("\n");
        }
        System.out.print(sb);
    }
}