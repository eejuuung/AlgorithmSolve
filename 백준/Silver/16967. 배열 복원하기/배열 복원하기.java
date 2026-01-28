import java.io.*;
import java.util.*;

public class Main {
    public static class Node {
        int num;
        boolean cross;

        Node(int num) {
            this.num = num;
            this.cross = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(stz.nextToken());
        int W = Integer.parseInt(stz.nextToken());
        int X = Integer.parseInt(stz.nextToken());
        int Y = Integer.parseInt(stz.nextToken());

        Node[][] B = new Node[H + X][W + Y];

        for (int i = 0; i < H + X; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++) {
                B[i][j] = new Node(Integer.parseInt(stz.nextToken()));
            }
        }

        for (int bi = X; bi < H; bi++) {
            for (int bj = Y; bj < W; bj++) {
                B[bi][bj].cross = true;
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (B[i][j].cross && B[i][j].num != 0)
                    B[i][j].num -= B[i - X][j - Y].num;

                sb.append(B[i][j].num);
                if (j < W - 1)
                    sb.append(" ");
            }
            if (i < H - 1)
                sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}