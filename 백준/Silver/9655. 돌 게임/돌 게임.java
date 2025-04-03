import java.io.*;
import java.util.*;

public class Main {

    public static class Turn {
        int who;
        int stone;

        public Turn(int who, int stone) {
            this.who = who;
            this.stone = stone;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[][] bmap = new boolean[N][N];

        Queue<Turn> que = new ArrayDeque<>();
        que.offer(new Turn(0, N));

        while (!que.isEmpty()) {
            Turn nowTurn = que.poll();
            int person = nowTurn.who;

            if (nowTurn.stone - 1 == 0 || nowTurn.stone - 3 == 0) {
                System.out.println((nowTurn.who == 0 ? "SK" : "CY"));
                return;
            }

            if (nowTurn.stone - 1 >= 0 && !bmap[person][nowTurn.stone - 1]) {
                bmap[person][nowTurn.stone - 1] = true;
                que.offer(new Turn((person == 0 ? 1 : 0), nowTurn.stone - 1));
            }

            if (nowTurn.stone - 3 >= 0 && !bmap[person][nowTurn.stone - 3]) {
                bmap[person][nowTurn.stone - 3] = true;
                que.offer(new Turn((person == 0 ? 1 : 0), nowTurn.stone - 3));
            }

        }


    }
}
