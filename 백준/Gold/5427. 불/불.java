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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        //오, 아, 왼, 위
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(stz.nextToken());
            int H = Integer.parseInt(stz.nextToken());
            char[][] map = new char[H][W];
            Queue<Node> que = new ArrayDeque<>();
            Queue<Node> fire = new ArrayDeque<>();


            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '@') {
                        map[i][j] = '-';
                        que.offer(new Node(i, j, 0));
                    } else if (map[i][j] == '*') {
                        fire.add(new Node(i, j, 0));
                    }
                }
            }

            boolean getOut = false;
            int answer = -1;
            while (!que.isEmpty()) {
                // 불 체크
                int fireCount = fire.size();
                for (int i = 0; i < fireCount; i++) {
                    Node nowFire = fire.poll();
                    for (int k = 0; k < 4; k++) {
                        int fy = nowFire.y + dy[k];
                        int fx = nowFire.x + dx[k];
                        if (fy < 0 || fx < 0 || fy >= H || fx >= W || map[fy][fx] == '#' || map[fy][fx] == '*') {
                            continue;
                        }
                        map[fy][fx] = '*';
                        fire.offer(new Node(fy, fx, 0));
                    }
                }


                // 상근이 이동
                int qSize = que.size();
                for (int i = 0; i < qSize; i++) {
                    Node nowNode = que.poll();

                    for (int k = 0; k < 4; k++) {
                        int fy = nowNode.y + dy[k];
                        int fx = nowNode.x + dx[k];
                        if (fy < 0 || fx < 0 || fy >= H || fx >= W) {
                            answer = nowNode.time + 1;
                            getOut = true;
                            break;
                        } else if (map[fy][fx] != '.') {
                            continue;
                        }

                        map[fy][fx] = '-';
                        que.offer(new Node(fy, fx, nowNode.time + 1));
                    }
                }
                if (getOut) {
                    break;
                }
            }

            sb.append(answer == -1 ? "IMPOSSIBLE\n" : answer + "\n");

        }

        bw.write(sb.toString());
        bw.flush();
    }
}