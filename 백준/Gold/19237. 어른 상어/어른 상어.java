import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static class Fish{
        int y;
        int x;
        int dir;
        int[][] sharMap;
        boolean live;

        Fish(int y, int x, int dir){
            this.y = y;
            this.x = x;
            this.dir = dir;
            sharMap = new int[4][4];
            live = true;
        }
    }

    public static class DFish{
        int ny;
        int nx;
        int ry;
        int rx;
        int dir;

        DFish(){
            this.ny = 0;
            this.nx = 0;
            this.ry = 0;
            this.rx = 0;
            this.dir = 0;
        }
        DFish(int ny, int nx, int ry, int rx, int dir){
            this.ny = ny;
            this.nx = nx;
            this.ry = ry;
            this.rx = rx;
            this.dir = dir;
        }
    }

    public static class Fool{
        int num; // 번호
        int smell;  // 냄새
        boolean sharkHere;// 상어위치

        Fool(){
            num = 0;
            smell = 0;
            sharkHere = false;
        }
    }

    public static int N,M,K;
    public static Fool[][] smellMap;
    public static Fish[] shark;

    public static int totalShark = 0;
    public static int totalTime = 0;

    //1=위, 2=아래, 3=왼쪽, 4=오른쪽
    public static int[] dy = { -1,1,0,0 };
    public static int[] dx = { 0,0,-1,1 };

    public static void MoveShark() {
        /*
                상어 이동조건
            1. 먼저 인접한 칸 중 아무냄새가 없는 칸의 방향
            2. 그런칸이 없으면 자신의 냄새가 있는 칸의 방향
            3. 1~2중 가능한 칸이 여러개인 경우 우선순위에 맞게 이동
            -. 이동시에는 방금 이동한 방향이 보고있는 방향으로 상어변경.
            -. 모든상어 이동 후 여러마리의 상어가 남아 있으면, 가장 적은 번호를 가진 상어를 제외하고 쫓겨남.
            -. Shark이동 후 shark 없는자리는 smell-1 -> 0되면 num,smell 초기화
            -. 상어 위치는 바로 바꾸지 않고 값만 가지고 있다가 모든 상어 이동자리확인 후 변경

         */

        for(int i=0;i<=1000;i++){

            //상어 1마리만 남았을때
            if (totalShark == 1) {
                totalTime = i;
                break;
            }

            DFish[] checkShark = new DFish[M];
            for(int j=0;j<M;j++){
                checkShark[j] = new DFish();
            }

            // 상어 1번부터 이동
            for (int j = 0; j < M; j++){
                //우선 살아있는 상어인지 체크
                if (!shark[j].live)
                    continue;

                boolean sm = false;
                //상어 이동조건1. 인접칸중 아무냄새가 없는 칸
                int pd = shark[j].dir -1;
                int[] pa = {shark[j].sharMap[pd][0], shark[j].sharMap[pd][1], shark[j].sharMap[pd][2], shark[j].sharMap[pd][3] };
                for (int ii = 0; ii < 4; ii++) {
                    int py = shark[j].y + dy[pa[ii] - 1];
                    int px = shark[j].x + dx[pa[ii] - 1];

                    if(py<0 || px<0 || py>=N || px>=N)
                        continue;

                    if(smellMap[py][px].smell == 0){
                        //인접칸 이동가능 (다중,단일)
                        checkShark[j].ny = py;
                        checkShark[j].nx = px;
                        checkShark[j].ry = shark[j].y;
                        checkShark[j].rx = shark[j].x;
                        checkShark[j].dir = pa[ii];
                        sm = true;
                        break;
                    }
                }

                if(!sm){
                    //인접칸 이동불가
                    //상어 이동조건2. 자신의 냄새가 있는칸
                    for (int jj = 0; jj < 4; jj++){
                        int py = shark[j].y + dy[pa[jj] - 1];
                        int px = shark[j].x + dx[pa[jj] - 1];

                        if(py<0 || px<0 || py>=N || px>=N){
                            continue;
                        }

                        if(smellMap[py][px].num == j+1){
                            //내구역 이동가능 (다중,단일)
                            //상어위치 바꿔주고 이동한 위치에 세팅
                            checkShark[j].ny = py;
                            checkShark[j].nx = px;
                            checkShark[j].ry = shark[j].y;
                            checkShark[j].rx = shark[j].x;
                            checkShark[j].dir = pa[jj];
                            sm = true;
                            break;
                        }
                    }

                    if (!sm) {
                        //내구역 이동불가, 인접칸도 없고, 이동도 불가능함.
                        // 그외에는 현재자리에서 냄새만 갱신하고 중단.
                        smellMap[shark[j].y][shark[j].x].smell = K+1;

                    }
                }
            }

            //이제 모든 상어 이동
            for(int v=0;v<M;v++){
                //살아있는 상어인지 체크
                if(!shark[v].live){
                    continue;
                }

                //빈자리 인지 체크
                if(!smellMap[checkShark[v].ny][checkShark[v].nx].sharkHere){
                    //이전상어자리 지워줌
                    smellMap[checkShark[v].ry][checkShark[v].rx].sharkHere = false;
                    shark[v].y = checkShark[v].ny;
                    shark[v].x = checkShark[v].nx;
                    shark[v].dir = checkShark[v].dir;
                    smellMap[checkShark[v].ny][checkShark[v].nx].sharkHere = true;
                    smellMap[checkShark[v].ny][checkShark[v].nx].num = v + 1;
                    smellMap[checkShark[v].ny][checkShark[v].nx].smell = K + 1;
                }
                else{
                    //빈자리 아니면 상어 죽임
                    totalShark--;
                    shark[v].live = false;
                    smellMap[checkShark[v].ry][checkShark[v].rx].sharkHere = false;
                }
            }

            //모든 상어의 이동이 끝난 후 냄새 -1, 냄새 0이 된곳은 num도 지워주기
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < N; m++) {
                    if (smellMap[n][m].smell > 1) {
                        smellMap[n][m].smell--;
                    }
                    else if (smellMap[n][m].smell == 1) {
                        smellMap[n][m].smell = 0;
                        smellMap[n][m].num = 0;
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        shark = new Fish[M];
        smellMap = new Fool[N][N];
        totalShark = M;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                smellMap[i][j] = new Fool();
            }
        }
        for(int i=0;i<N;i++){
            stz = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                smellMap[i][j].num = Integer.parseInt(stz.nextToken());
                if(smellMap[i][j].num!=0){
                    shark[smellMap[i][j].num-1] = new Fish(i,j,0);
                    smellMap[i][j].smell = K;
                    smellMap[i][j].sharkHere = true;
                }
            }
        }

        stz = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            shark[i].dir =Integer.parseInt(stz.nextToken());
        }

        for(int k=0;k<M;k++){
            for(int i=0;i<4;i++){
                stz = new StringTokenizer(br.readLine());
                for(int j=0;j<4;j++){
                    shark[k].sharMap[i][j] = Integer.parseInt(stz.nextToken());
                }
            }
        }

        MoveShark();

        if(totalTime == 0)
            System.out.println("-1");
        else
            System.out.println(totalTime);


    }
}