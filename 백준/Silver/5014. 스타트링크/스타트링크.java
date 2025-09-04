import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int stair;
        int time;

        Node(int stair, int time) {
            this.stair = stair;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine(), " ");

        int F = Integer.parseInt(stz.nextToken());
        int S = Integer.parseInt(stz.nextToken());
        int G = Integer.parseInt(stz.nextToken());
        int U = Integer.parseInt(stz.nextToken());
        int D = Integer.parseInt(stz.nextToken());
        boolean[] visit = new boolean[F + 1];
        int answer = -1;

        if ((U == 0 && S < G) || (D == 0 && S > G)) {
            System.out.println("use the stairs");
            return;
        }

        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(S, 0));
        visit[S] = true;

        while (!que.isEmpty()) {
            Node nowNode = que.poll();

            if (nowNode.stair == G) {
                answer = nowNode.time;
                break;
            }

            if (nowNode.stair + U <= F && !visit[nowNode.stair + U]) {
                que.offer(new Node(nowNode.stair + U, nowNode.time + 1));
                visit[nowNode.stair + U] = true;
            }

            if (nowNode.stair - D >= 1 && !visit[nowNode.stair - D]) {
                que.offer(new Node(nowNode.stair - D, nowNode.time + 1));
                visit[nowNode.stair - D] = true;
            }
        }

        System.out.println(answer == -1 ? "use the stairs" : answer);

    }
}
