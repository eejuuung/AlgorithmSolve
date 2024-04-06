import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class Fish{
        int y;
        int x;
        int dir;
        boolean live;

        Fish(int y, int x, int dir,boolean live){
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.live = live;
        }
    }

    public static final int MAX = 4;
    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    public static int[] dy = { -1,-1,0,1,1,1,0,-1 };
    public static int[] dx = { 0,-1,-1,-1,0,1,1,1 };

    public static Fish[] fish;
    public static Fish shark;
    public static int TotalEat;
    public static int[][] map;

    public static void BackFish(int sy, int sx, int sd, int eatF){
        int[][] cmap = new int[MAX][MAX];
        Fish cShark;
        Fish[] cFish = new Fish[16];

        //식사갯수MAX 변경해주기
        if (TotalEat < eatF)
            TotalEat = eatF;

        //물고기 이동
        MoveFish();

        //이동 후 배열 복사
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                cmap[i][j] = map[i][j];
            }
        }


        //샤크정보복사
        cShark = new Fish(shark.y,shark.x,shark.dir,shark.live);

        //Fish 정보복사
        for (int i = 0; i < 16; i++) {
            int y = fish[i].y;
            int x = fish[i].x;
            int dir = fish[i].dir;
            boolean live = fish[i].live;

            cFish[i] = new Fish(y,x,dir,live);
        }

        //*3만큼 이동범위 확인해서 백트래킹
        for (int k = 1; k <= 3; k++){
            //상어 이동위치 상어는 회전하지 않는다. 즉, D고정
            int pd = sd - 1;
            int py = sy + (dy[pd] * k);
            int px = sx + (dx[pd] * k);

            //범위를 넘거나 이동위치가 비어있으면 들어가지 않음.
            if(py<0 || px<0 || py>=MAX || px>=MAX )
                continue;
            if(map[py][px] == 0)
                continue;

            //범위 넘지않고 먹힌물고기도 아니면 상어가 먹음
            //상어가 먹고 먹은 물고기의 위치랑 방향을 가져감.
            int eatyou = map[py][px];
            map[py][px] = 0;
            shark.y = py;
            shark.x = px;
            shark.dir = fish[eatyou - 1].dir;
            fish[eatyou - 1].live = true;
            BackFish(shark.y, shark.x, shark.dir, eatF + eatyou);

            //백트래킹 돌아올때 배열복사한거 돌려주기, 상어위치 돌려주기, 물고기정보 돌려주기
            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    map[i][j] = cmap[i][j];
                }
            }
            shark = new Fish(cShark.y,cShark.x,cShark.dir,cShark.live);
            for (int i = 0; i < 16; i++) {
                int y = cFish[i].y;
                int x = cFish[i].x;
                int dir = cFish[i].dir;
                boolean live = cFish[i].live;

                fish[i] = new Fish(y,x,dir,live);
            }
        }
    }

    public static void MoveFish() {
        for (int i = 0; i < 16; i++) {
            int y = fish[i].y;
            int x = fish[i].x;
            int d = fish[i].dir;

            //먹힌 물고기는 건너뜀
            if (fish[i].live)
                continue;

            //상어 위치면 건너뜀
            if (y == shark.y && x == shark.x)
                continue;

            //바라보는 방향 갈수있는지 체크
            for (int j = 0; j < 8; j++) {
                int pd = (d - 1 + j) % 8;
                int py = y + dy[pd];
                int px = x + dx[pd];

                //이동위치가 상어 위치면 건너뜀
                if ( py == shark.y && px == shark.x)
                    continue;

                // 범위넘는다면
                if(py<0 || px<0 || py>=MAX || px>=MAX)
                    continue;

                //범위 넘지않고 상어위치도 아니면 해당자리랑 자리바꿈
                int cxy = map[py][px];
                if (cxy == 0) {
                    //바꿀위치 비어있음!
                    map[fish[i].y][fish[i].x] = 0;
                    fish[i].y = py;
                    fish[i].x = px;
                    fish[i].dir = pd + 1;
                    map[py][px] = i + 1;
                }
                else {
                    //바꿀위치에 물고기 있움! 위치 바꿔줘야할때
                    int fishY = fish[cxy - 1].y;
                    int fishX = fish[cxy - 1].x;
                    int fishDir = fish[cxy - 1].dir;
                    boolean fishLive = fish[cxy - 1].live;
                    Fish cFish = new Fish(fishY,fishX,fishDir,fishLive);
                    fish[cxy - 1].y = fish[i].y;
                    fish[cxy - 1].x = fish[i].x;
                    fish[i].y = cFish.y;
                    fish[i].x = cFish.x;
                    fish[i].dir = pd + 1;
                    map[fish[i].y][fish[i].x] = i + 1;
                    map[fish[cxy - 1].y][fish[cxy - 1].x] = cxy;
                }
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

        int FirstEat = 0;
        fish = new Fish[16];
        map = new int[MAX][MAX];
        TotalEat = 0;

        for(int i=0;i<MAX;i++){
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for (int j=0;j<MAX;j++){
                int num = Integer.parseInt(stz.nextToken());
                int dir = Integer.parseInt(stz.nextToken());

                fish[num-1] = new Fish(i,j,dir,false);
                map[i][j] = num;
            }
        }

        shark = new Fish(0,0,fish[map[0][0]-1].dir,true);
        fish[map[0][0]-1].live = true;
        FirstEat = map[0][0];
        map[0][0] = 0;

        BackFish(0, 0, shark.dir, 0);

        System.out.println(TotalEat+ FirstEat);

    }
}