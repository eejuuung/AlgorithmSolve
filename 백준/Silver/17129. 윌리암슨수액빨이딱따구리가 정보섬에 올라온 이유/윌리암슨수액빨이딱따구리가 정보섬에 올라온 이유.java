import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int y;
        int x;
        int time;

        Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int[][] island = new int[N][M];

        // 오 아 왼 위
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        Node william = null;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                island[i][j] = str.charAt(j) - '0';

                if (island[i][j] == 2) {
                    william = new Node(i, j, 0);
                    island[i][j] = -1;
                }
            }
        }

        boolean isout = false;
        Queue<Node> que = new ArrayDeque<>();
        que.offer(william);

        while (!que.isEmpty()) {
            Node nowNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int fy = nowNode.y + dy[i];
                int fx = nowNode.x + dx[i];

                if (fy < 0 || fx < 0 || fy >= N || fx >= M || island[fy][fx] == -1 || island[fy][fx] == 1)
                    continue;

                if (island[fy][fx] >= 2 && island[fy][fx] <= 5) {
                    isout = true;
                    sb.append("TAK").append("\n").append(nowNode.time + 1);
                    break;
                }

                que.offer(new Node(fy, fx, nowNode.time + 1));
                island[fy][fx] = -1;
            }

            if (isout)
                break;
        }

        if (!isout)
            sb.append("NIE");

        System.out.println(sb);
    }
}
