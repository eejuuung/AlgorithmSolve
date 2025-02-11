import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N개의 책을 최대 M개씩 들 수 있으므로 양수와 음수에서 절대값으로 M개의 집합을 지어준다.
 * 그 집합이 최소의 거리만큼 걸을 수 있는 1회 집단이다.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        int answer = 0;
        PriorityQueue<Integer> plusQue = new PriorityQueue<>((p1, p2) -> p2 - p1);
        PriorityQueue<Integer> minusQue = new PriorityQueue<>((p1, p2) -> p1 - p2);

        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(stz.nextToken());
            if (num < 0)
                minusQue.offer(num);
            else
                plusQue.offer(num);
        }

        Queue<Integer> pQue = new PriorityQueue<>();
        boolean isF = false;
        int nowW = 0;
        while (!plusQue.isEmpty()) {
            for (int i = 0; i < M && !plusQue.isEmpty(); i++) {
                if (!isF) {
                    isF = true;
                    nowW = plusQue.poll();
                } else
                    plusQue.poll();
            }
            isF = false;
            pQue.offer(Math.abs(nowW));
        }

        nowW = 0;
        isF = false;
        while (!minusQue.isEmpty()) {
            for (int i = 0; i < M && !minusQue.isEmpty(); i++) {
                if (!isF) {
                    isF = true;
                    nowW = minusQue.poll();
                } else
                    minusQue.poll();
            }
            isF = false;
            pQue.offer(Math.abs(nowW));
        }

        while (!pQue.isEmpty()) {
            nowW = pQue.poll();
            if (pQue.isEmpty()) {
                answer = answer + nowW;
            } else {
                answer = answer + (nowW * 2);
            }
        }
        System.out.println(answer);

    }
}