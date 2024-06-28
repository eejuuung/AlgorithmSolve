import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class DiceM{
        int dy;
        int dx;
        int dd;
        public DiceM(int dy, int dx, int dd){
            this.dy = dy;
            this.dx = dx;
            this.dd = dd;
        }
    }

    public static class Pair{
        int qy;
        int qx;
        public Pair(int qy, int qx){
            this.qy = qy;
            this.qx = qx;
        }
    }

    public static int[][] map;
    public static int RN, RM, RK;
    public static int[] Dice = { 2,4,1,3,5,6 };
    //위,아,오,왼
    public static DiceM diceMemory = new DiceM(0,0,3);
    public static int[] ddy = { -1,1,0,0 };
    public static int[] ddx = { 0,0,1,-1 };

    public static void RunDICE(int dy,int dx,int dd) {
        //만약 이동방향에 칸이 없다면 반대로 굴러가야함

        int cdy = dy + ddy[dd - 1];
        int cdx = dx + ddx[dd - 1];

        if (cdy < 0 || cdx < 0 || cdy >= RN || cdx >= RM) {
            if (dd == 1)
                dd = 2;
            else if (dd == 2)
                dd = 1;
            else if (dd == 3)
                dd = 4;
            else if (dd == 4)
                dd = 3;
        }
        dy = dy + ddy[dd - 1];
        dx = dx + ddx[dd - 1];
        int copyD = 0;
        switch (dd)
        {
            case 1:	//위쪽이동
                copyD = Dice[0];
                Dice[0] = Dice[2];
                Dice[2] = Dice[4];
                Dice[4] = Dice[5];
                Dice[5] = copyD;
                break;
            case 2:	//아래쪽 이동
                copyD = Dice[0];
                Dice[0] = Dice[5];
                Dice[5] = Dice[4];
                Dice[4] = Dice[2];
                Dice[2] = copyD;
                break;
            case 3:	//오른쪽 이동
                copyD = Dice[1];
                Dice[1] = Dice[5];
                Dice[5] = Dice[3];
                Dice[3] = Dice[2];
                Dice[2] = copyD;
                break;
            case 4:	//왼른쪽 이동
                copyD = Dice[1];
                Dice[1] = Dice[2];
                Dice[2] = Dice[3];
                Dice[3] = Dice[5];
                Dice[5] = copyD;

                break;
        }

        diceMemory.dy = dy;
        diceMemory.dx = dx;
        diceMemory.dd = dd;
    }

    public static int ScoreCal(int dy, int dx,int dd) {
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(dy,dx));
        boolean[][] checkmap = new boolean[21][21];
        int totalScore = 1;

        checkmap[dy][dx] = true;
        while (!que.isEmpty())
        {
            dy = que.peek().qy;
            dx = que.peek().qx;

            //오
            if (dx + 1 < RM && map[dy][dx + 1] == dd && !checkmap[dy][dx + 1]) {
                checkmap[dy][dx + 1] = true;
                que.offer(new Pair(dy,dx+1));
                totalScore++;
            }
            //위
            if (dy - 1 >= 0 && map[dy - 1][dx] == dd && !checkmap[dy - 1][dx]) {
                checkmap[dy - 1][dx] = true;
                que.offer(new Pair(dy-1,dx));
                totalScore++;
            }
            //아
            if (dy + 1 < RN && map[dy+1][dx] == dd && !checkmap[dy + 1][dx]) {
                checkmap[dy + 1][dx] = true;
                que.offer(new Pair(dy+1,dx));
                totalScore++;
            }
            //왼
            if (dx - 1 >= 0 && map[dy][dx - 1] == dd && !checkmap[dy][dx - 1]) {
                checkmap[dy][dx - 1] = true;
                que.offer(new Pair(dy,dx-1));
                totalScore++;
            }

            que.poll();
        }
        totalScore = totalScore * dd;
        return totalScore;
    }

    public static int RunGame() {
        int totalscore = 0;
        for (int rgame = 0; rgame < RK; rgame++) {
            //1. 주사위가 이동방향으로 한칸 이동함. 이동방향에 칸이 없다면, 이동방향 반대로 한 다음 한칸 굴러감
            RunDICE(diceMemory.dy, diceMemory.dx, diceMemory.dd);
            //2. 주사위가 도착한 칸에 있는 점수 획득
            totalscore += ScoreCal(diceMemory.dy, diceMemory.dx, map[diceMemory.dy][diceMemory.dx]);
            //3. 주사위의 아랫면에 있는 정수 A와 주사위칸에있는 정수B를 비교해 이동방향을 결정함
            if (Dice[5] > map[diceMemory.dy][diceMemory.dx]) {	//3-1. A>B 인 경우 이동방향을 90도 시계방향으로 회전시킴
                if (diceMemory.dd == 1)
                    diceMemory.dd = 3;
                else if (diceMemory.dd == 2)
                    diceMemory.dd = 4;
                else if (diceMemory.dd == 3)
                    diceMemory.dd = 2;
                else if (diceMemory.dd == 4)
                    diceMemory.dd = 1;
            }
            else if (Dice[5] < map[diceMemory.dy][diceMemory.dx]) {	//3-2. A<B 인 경우 이방향향을 90도 반시계 방향으로 회전시킴
                if (diceMemory.dd == 1)
                    diceMemory.dd = 4;
                else if (diceMemory.dd == 2)
                    diceMemory.dd = 3;
                else if (diceMemory.dd == 3)
                    diceMemory.dd = 1;
                else if (diceMemory.dd == 4)
                    diceMemory.dd = 2;
            }
        }
        return totalscore;
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[21][21];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        RN = Integer.parseInt(stz.nextToken());
        RM = Integer.parseInt(stz.nextToken());
        RK = Integer.parseInt(stz.nextToken());

        for(int i=0;i<RN;i++){
            stz = new StringTokenizer(br.readLine());
            for(int j=0;j<RM;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        int answer = RunGame();
        System.out.println(answer);

    }
}