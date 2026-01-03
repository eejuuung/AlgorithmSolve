import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int num;
        int jump;

        Node(int num, int jump) {
            this.num = num;
            this.jump = jump;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(0, 0));
        int isout = -1;
        while (!que.isEmpty()) {
            Node nowNode = que.poll();
            if (nowNode.num == N - 1) {
                isout = nowNode.jump;
                break;
            }
            if (arr[nowNode.num] == 0)
                continue;

            for (int i = 1; i <= arr[nowNode.num]; i++) {
                if (nowNode.num + i < N) {
                    que.offer(new Node(nowNode.num + i, nowNode.jump + 1));
                } else
                    break;
            }
            arr[nowNode.num] = 0;
        }

        System.out.println(isout);

    }
}