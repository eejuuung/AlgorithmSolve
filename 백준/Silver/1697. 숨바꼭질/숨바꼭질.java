import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 200_000;

    static class Node {
        int n;
        int time;

        Node(int n, int time) {
            this.n = n;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        boolean[] visit = new boolean[INF + 1];
        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());

        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(N, 0));
        visit[N] = true;

        if (N == K) {
            System.out.println("0");
            return;
        }

        while (!que.isEmpty()) {

            Node nowNode = que.poll();

            int mN = nowNode.n - 1;
            int pN = nowNode.n + 1;
            int xN = nowNode.n * 2;

            if (mN == K || pN == K || xN == K) {
                System.out.println(nowNode.time + 1);
                return;
            }

            if (mN >= 0 && mN <= INF && !visit[mN]) {
                que.offer(new Node(mN, nowNode.time + 1));
                visit[mN] = true;
            }
            if (pN >= 0 && pN <= INF && !visit[pN]) {
                que.offer(new Node(pN, nowNode.time + 1));
                visit[pN] = true;
            }

            if (xN >= 0 && xN <= INF && !visit[xN]) {
                que.offer(new Node(xN, nowNode.time + 1));
                visit[xN] = true;
            }
        }

    }
}
