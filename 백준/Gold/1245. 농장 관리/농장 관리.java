import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Pair{
        int y;
        int x;
        Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static int N,M;
    public static int answer;
    public static int[][] map;
    //X좌표와 Y좌표차이 모두 1이하인곳 = 8방향
    // 위, 위오, 오, 오아, 아, 아왼, 왼, 왼위
    public static int[] dy = {-1,-1,0,1,1,1,0,-1};
    public static int[] dx = {0,1,1,1,0,-1,-1,-1};

    public static int[][] zipMap;
    public static int zipNum;

    public static void makeZip(int y, int x){
        // 우선 산봉우리 집합 구하기
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(y,x));

        boolean[][] bMap = new boolean[N][M];
        bMap[y][x] = true;

        while(!que.isEmpty()){

            Pair nowP = que.poll();
            zipMap[nowP.y][nowP.x] = zipNum;

            for(int i=0;i<8;i++){
                int fy = nowP.y + dy[i];
                int fx = nowP.x + dx[i];

                if(fy<0 || fx<0 || fy>=N || fx>=M || bMap[fy][fx] || map[fy][fx]!=map[y][x])
                    continue;

                que.offer(new Pair(fy,fx));
                bMap[fy][fx] = true;
            }
        }

        zipNum++;
    }

    public static void checkTop(int num){

        int gizun = 0;
        Queue<Pair> que = new ArrayDeque<>();
        boolean[][] bMap = new boolean[N][M];

        //넘어온 num기준으로 que에 담기
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(zipMap[i][j] == num){
                    que.offer(new Pair(i,j));
                    bMap[i][j] = true;
                    gizun = map[i][j];
                }
            }
        }

        //담긴 que 주위가 전부 현재높이보다 작은지 체크할것.
        boolean isOkay = true;
        while (!que.isEmpty()){
            Pair nowP = que.poll();

            for(int i=0;i<8;i++){
                int fy = nowP.y + dy[i];
                int fx = nowP.x + dx[i];

                if(fy<0 || fx<0 || fy>=N || fx>=M || bMap[fy][fx])
                    continue;

                if(map[fy][fx]>=gizun){
                    isOkay = false;
                    break;
                }

                bMap[fy][fx] = true;
            }

            if(!isOkay)
                break;
        }

        if(isOkay)
            answer++;

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        answer = 0;
        map = new int[N][M];
        zipMap = new int[N][M];
        zipNum = 1;

        for(int i=0;i<N;i++){
            stz = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        // 1. 집합먼저 구해서 묶어주기
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                //현재 위치값이 0이 아닌경우만 집합이 될 수 있음. 또한 집합으로 안묶인 곳만 체크
                if(map[i][j]!=0 && zipMap[i][j]==0){
                    makeZip(i,j);
                }
            }
        }
        
        // 2. 만들어진 집합 기준으로 봉우리 여부 체크
        for(int i=1;i<zipNum;i++){
            checkTop(i);
        }

        System.out.println(answer);

    }
}