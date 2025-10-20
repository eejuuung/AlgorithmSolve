import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        // 오, 아, 왼, 위
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int R = Integer.parseInt(stz.nextToken());
        int C = Integer.parseInt(stz.nextToken());
        char[][] arr = new char[R][C];
        Node answer = new Node(0, 0); // wolf, sheep

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] != '#') {
                    Node count = new Node(0, 0); // wolf, sheep
                    Queue<Node> que = new ArrayDeque<>();
                    que.offer(new Node(i, j));
                    if (arr[i][j] == 'v')
                        count.y++;
                    else if (arr[i][j] == 'o')
                        count.x++;
                    arr[i][j] = '#';
                    while (!que.isEmpty()) {
                        Node nowNode = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int fy = nowNode.y + dy[k];
                            int fx = nowNode.x + dx[k];

                            if (fy < 0 || fx < 0 || fy >= R || fx >= C || arr[fy][fx] == '#')
                                continue;

                            if (arr[fy][fx] == 'v')
                                count.y++;
                            else if (arr[fy][fx] == 'o')
                                count.x++;

                            arr[fy][fx] = '#';
                            que.offer(new Node(fy, fx));
                        }
                    }
                    if (count.x > count.y) {
                        answer.x += count.x;
                    } else {
                        answer.y += count.y;
                    }
                }
            }
        }
        System.out.println(answer.x + " " + answer.y);
    }
}
