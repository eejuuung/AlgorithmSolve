import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Paper {
        int num;
        int importance;

        public Paper(int num, int importance) {
            this.num = num;
            this.importance = importance;
        }
    }

    public static Queue<Paper> que;
    public static Queue<Integer> pQue;
    public static StringBuilder sb;

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stz.nextToken());
            int M = Integer.parseInt(stz.nextToken());

            que = new ArrayDeque<>();
            pQue = new PriorityQueue<>(Collections.reverseOrder());
            stz = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int val = Integer.parseInt(stz.nextToken());
                que.offer(new Paper(i, val));
                pQue.offer(val);
            }
            sb.append(printerQueue(M)).append("\n");
        }
    }

    public static int printerQueue(int outNum) {
        int cnt = 0;
        int num = -1;

        while (num != outNum) {
            int outVal = pQue.poll();
            while (true) {
                Paper nowPaper = que.peek();
                if (nowPaper.importance == outVal) {
                    break;
                } else {
                    que.offer(que.poll());
                }
            }
            Paper outPaper = que.poll();
            cnt++;
            num = outPaper.num;

        }

        return cnt;
    }


    public static void main(String[] args) throws Exception {
        init();
        System.out.print(sb);
    }
}