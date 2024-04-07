import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static class FireBall{
        int r;  //y
        int c;  //x  
        int m;  //질량
        int s;  //속도
        int d;  //방향

        FireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static class Map{
        int totalM;
        int totalS;
        int totalNum;
        ArrayList<Integer> dirList;
        Map(){
            totalM = 0;
            totalS = 0;
            totalNum = 0;
            dirList = new ArrayList<>();
        }
    }

    public static int N,M,K;
    public static ArrayList<FireBall> fireballs;

    //위, 위오, 오, 아오, 아, 아왼, 왼, 위왼
    public static int[] dy = {-1,-1,0,1,1,1,0,-1};
    public static int[] dx = {0,1,1,1,0,-1,-1,-1};

    public static void Magic(){

        //map 생성, 초기화
        Map[][] map = new Map[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = new Map();
            }
        }
        // 모든 파이어볼 이동
        while (!fireballs.isEmpty()){
            int y = fireballs.get(0).r;
            int x = fireballs.get(0).c;
            int s = fireballs.get(0).s;
            int d = fireballs.get(0).d;
            int m = fireballs.get(0).m;

            for(int runt = 0;runt<s;runt++){

                int fy = movexy(y + dy[d]);
                int fx = movexy(x + dx[d]);

                y = fy;
                x = fx;
            }

            map[y][x].totalM += m;
            map[y][x].totalS += s;
            map[y][x].totalNum += 1;
            map[y][x].dirList.add(d);
            fireballs.remove(0);

        }

        //map의 파이어볼 정리
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j].totalNum == 1){
                    fireballs.add(new FireBall(i,j,map[i][j].totalM,map[i][j].totalS,map[i][j].dirList.get(0)));
                }
                else if(map[i][j].totalNum>1){
                    int m = map[i][j].totalM/5;
                    //질량이 0인 파이어볼은 없어지므로 체크 X
                    if(m==0)
                        continue;

                    int s = map[i][j].totalS / map[i][j].totalNum;
                    boolean isokay = true;
                    // 짝수 = true, 홀수  = false;
                    boolean dir = (map[i][j].dirList.get(0) % 2 == 0);
                    for(int k=1;k<map[i][j].dirList.size();k++){
                        if(dir){
                            if(map[i][j].dirList.get(k)%2!=0){
                                isokay = false;
                                break;
                            }
                        }
                        else{
                            if(map[i][j].dirList.get(k)%2==0){
                                isokay = false;
                                break;
                            }
                        }
                    }

                    // true = 0,2,4,6 / false = 1,3,5,7
                    if(!isokay){
                        fireballs.add(new FireBall(i,j,m,s,1));
                        fireballs.add(new FireBall(i,j,m,s,3));
                        fireballs.add(new FireBall(i,j,m,s,5));
                        fireballs.add(new FireBall(i,j,m,s,7));
                    }else{
                        fireballs.add(new FireBall(i,j,m,s,0));
                        fireballs.add(new FireBall(i,j,m,s,2));
                        fireballs.add(new FireBall(i,j,m,s,4));
                        fireballs.add(new FireBall(i,j,m,s,6));
                    }
                }
            }
        }


    }

    public static int movexy(int xy) {
        //0<->N 이어져있음
        if (xy < 0) {
            //들어온 값이 0보다 작을경우
            xy = Math.abs(xy) % N;
            xy = N - xy;
        }
        if (xy >= N) {
            //들어온 값이 지정map보다 클경우
            xy = xy % N;
        }
        return xy;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());
        fireballs = new ArrayList<>();

        for(int i=0;i<M;i++){
            stz = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());
            int m = Integer.parseInt(stz.nextToken());
            int s = Integer.parseInt(stz.nextToken());
            int d = Integer.parseInt(stz.nextToken());
            fireballs.add(new FireBall(r,c,m,s,d));
        }

        for(int i=0;i<K;i++){
            Magic();
        }
        int answer = 0;
        for(int i=0;i<fireballs.size();i++){
            answer += fireballs.get(i).m;
        }
        System.out.println(answer);

    }
}