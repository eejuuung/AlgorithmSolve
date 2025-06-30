import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int x;
        int time;

        Pair(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        boolean[] check = new boolean[100001];
        int answer = 0;

        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(N, 0));

        while (!que.isEmpty()) {
            Pair now = que.poll();

            if (now.x == K) {
                answer = now.time;
                break;
            }

            if ((now.x * 2) <= 100000 && !check[now.x * 2]) {
                check[now.x * 2] = true;
                que.offer(new Pair(now.x * 2, now.time));
            }
            if (now.x - 1 >= 0 && !check[now.x - 1]) {
                check[now.x - 1] = true;
                que.offer(new Pair(now.x - 1, now.time + 1));
            }
            if (now.x + 1 <= 100000 && !check[now.x + 1]) {
                check[now.x + 1] = true;
                que.offer(new Pair(now.x + 1, now.time + 1));
            }
        }

        System.out.println(answer);


    }
}
