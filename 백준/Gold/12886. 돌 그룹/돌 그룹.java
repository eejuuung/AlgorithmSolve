import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * a,b,c개의 돌을 가진 그룹이 존재할 때 a=b=c의 돌이 될 수 있는지 구하기
 * 여기서 핵심은 크기가 같지않은 두개의 돌의 개수를 고를 때
 * 작은쪽이 X, 큰쪽이 Y이고 작은쪽을 2X 큰쪽을 Y-X해주어서 돌을 섞어주는것.
 * 그런데 2X + Y - X = X + Y 즉, 전체의 돌의 총량은 변하지 않는다.
 * A,B,C의 총량은 <=500으로 2X를 해줫을때의 최대값은 1000이다.
 * 그러므로 [1001][1001][10001]을 해줘야 할것 같지만.
 * 총량이 변하지 않으므로 2차원 배열을 사용하여 [min][max]값만 방문체크를 해줘도 괜찮다 :)
 */
public class Main {
    public static class Group {
        int A;
        int B;
        int C;

        Group(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        boolean[][] bmap = new boolean[1500][1500];
        Group group = new Group(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));

        if ((group.A + group.B + group.C) % 3 != 0) {
            System.out.println("0");
            return;
        }

        Queue<Group> que = new ArrayDeque<>();
        que.offer(group);
        while (!que.isEmpty()) {
            Group nowG = que.poll();

            if (nowG.A == nowG.B && nowG.B == nowG.C) {
                System.out.println("1");
                return;
            }

            int minV = Math.min(Math.min(nowG.A, nowG.B), nowG.C);
            int maxV = Math.max(Math.max(nowG.A, nowG.B), nowG.C);
            int midV = nowG.A + nowG.B + nowG.C - minV - maxV;

            if (bmap[minV][maxV]) {
                continue;
            }

            bmap[minV][maxV] = true;
            if (minV != maxV) {
                que.offer(new Group(minV + minV, maxV - minV, midV));
            }
            if (minV != midV) {
                que.offer(new Group(minV + minV, midV - minV, maxV));
            }
            if (midV != maxV) {
                que.offer(new Group(midV + midV, maxV - midV, minV));
            }
        }
        System.out.println("0");

    }
}