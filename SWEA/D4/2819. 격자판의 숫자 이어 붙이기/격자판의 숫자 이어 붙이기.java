import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        int y;
        int x;
        int count;
        String str;

        Node(int y, int x, int count, String str) {
            this.y = y;
            this.x = x;
            this.count = count;
            this.str = str;
        }
    }

    // 오, 아, 왼, 위
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static final int CELL = 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz;
        int rtc = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= rtc; tc++) {
            Queue<Node> que = new ArrayDeque<>();
            HashSet<String> hashSet = new HashSet<>();
            String[][] square = new String[CELL][CELL];
            int count = 0;

            for (int i = 0; i < CELL; i++) {
                stz = new StringTokenizer(br.readLine());
                for (int j = 0; j < CELL; j++) {
                    square[i][j] = stz.nextToken();
                    que.offer(new Node(i, j, 1, square[i][j]));
                }
            }

            while (!que.isEmpty() && que.peek().count <= 7) {
                Node nowNode = que.poll();

                for (int i = 0; i < 4; i++) {
                    int fy = nowNode.y + dy[i];
                    int fx = nowNode.x + dx[i];

                    if (fy < 0 || fx < 0 || fy >= CELL || fx >= CELL)
                        continue;

                    if (nowNode.count + 1 >= 7) {
                        if (!hashSet.contains(nowNode.str + square[fy][fx])) {
                            count++;
                            hashSet.add(nowNode.str + square[fy][fx]);
                        }
                    } else {
                        que.offer(new Node(fy, fx, nowNode.count + 1, nowNode.str + square[fy][fx]));
                    }
                }
            }

            sb.append("#").append(tc).append(" ");
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }
}
