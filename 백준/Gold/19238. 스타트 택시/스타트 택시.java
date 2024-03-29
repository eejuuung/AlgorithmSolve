import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Pair{
        int sy;
        int sx;
        int ey;
        int ex;
        Pair(int sy, int sx, int ey, int ex){
            this.sy = sy;
            this.sx = sx;
            this.ey = ey;
            this.ex = ex;
        }
    }

    public static int N;    // map의 크기
    public static int M;    // 승객의 인원수
    public static int Fuel; // 연료

    public static int[][] map;  // 초기 map
    public static int[][][] cmap; // 각 위치별 거리map
    public static int[] emap;   // 각 손님의 도착위치 거리계산값
    public static ArrayList<Pair> sonnim;   // 손님의 전체리스트
    public static Pair taxi;    // taxi의 위치

    // 오, 아, 왼, 위
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};
    public static boolean[] sonnimdead;

    public static void drawCmap(){
        // M명의 손님의 거리별 위치를 체크
        for(int k=0;k<sonnim.size();k++){
            cmap[k][sonnim.get(k).sy][sonnim.get(k).sx] = 0;
            boolean[][] bmap = new boolean[N][N];
            Queue<Pair> que = new ArrayDeque<>();
            que.offer(new Pair(sonnim.get(k).sy,sonnim.get(k).sx,1,0));
            bmap[sonnim.get(k).sy][sonnim.get(k).sx] = true;
            while (!que.isEmpty()){
                Pair nowp = que.poll();

                for(int i=0;i<4;i++){
                    int fy = nowp.sy + dy[i];
                    int fx = nowp.sx + dx[i];

                    if(fy<0 || fx<0 || fy>=N || fx>=N || bmap[fy][fx] || map[fy][fx]!=0 ){
                        continue;
                    }

                    cmap[k][fy][fx] = nowp.ey;
                    bmap[fy][fx] = true;
                    que.offer(new Pair(fy,fx, nowp.ey+1, 0));
                }
            }
        }

        // M명의 손님의 도착위치 체크
        for(int k=0;k<sonnim.size();k++){
            if(sonnim.get(k).sy == sonnim.get(k).ey && sonnim.get(k).sx == sonnim.get(k).ex){
                emap[k] = 0;
            }
            boolean[][] bmap = new boolean[N][N];
            Queue<Pair> que = new ArrayDeque<>();
            que.offer(new Pair(sonnim.get(k).sy,sonnim.get(k).sx,1,0));
            bmap[sonnim.get(k).sy][sonnim.get(k).sx] = true;
            while (que != null && !que.isEmpty()){
                Pair nowp = que.poll();

                for(int i=0;i<4;i++){
                    int fy = nowp.sy + dy[i];
                    int fx = nowp.sx + dx[i];

                    if(fy<0 || fx<0 || fy>=N || fx>=N || bmap[fy][fx] || map[fy][fx] == 1){
                        continue;
                    }
                    if(fy == sonnim.get(k).ey && fx == sonnim.get(k).ex){
                        // 종료값
                        que = null;
                        emap[k] = nowp.ey;
                        break;
                    }

                    bmap[fy][fx] = true;
                    que.offer(new Pair(fy,fx, nowp.ey+1, 0));
                }
            }
        }

    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        Fuel = Integer.parseInt(stz.nextToken());
        map = new int[N][N];
        cmap = new int[M][N][N];
        emap = new int[M];
        sonnim = new ArrayList<>();
        sonnimdead = new boolean[M];
        int sonnimCnt = M;

        for(int i=0;i<N;i++){
            stz = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
                for(int k=0;k<M;k++){
                    cmap[k][i][j] = Integer.MAX_VALUE;
                }
            }
        }
        stz = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(stz.nextToken())-1;
        int sx = Integer.parseInt(stz.nextToken())-1;
        taxi = new Pair(sy,sx,0,0);
        for(int i=0;i<M;i++){
            stz = new StringTokenizer(br.readLine());
            sy = Integer.parseInt(stz.nextToken())-1;
            sx = Integer.parseInt(stz.nextToken())-1;
            int ey = Integer.parseInt(stz.nextToken())-1;
            int ex = Integer.parseInt(stz.nextToken())-1;
            sonnim.add(new Pair(sy,sx,ey,ex));
            emap[i] = Integer.MAX_VALUE;
        }

        
        //1. 우선 현재 손님의 위치별로 map의 전체 이동거리 뽑아주기
        drawCmap();

        while (Fuel > 0 && sonnimCnt!=0){
            //2. 현재 택시의 위치에서 가장 작은 이동거리값을 체크하고 최소값이 같다면 행, 열우선
            int minval = Integer.MAX_VALUE;
            int miny = Integer.MAX_VALUE;
            int minx = Integer.MAX_VALUE;
            int minnum = Integer.MAX_VALUE;

            for(int i=0;i<sonnim.size();i++){
                if(sonnimdead[i])
                    continue;

                if(cmap[i][taxi.sy][taxi.sx]<minval){
                    minval = cmap[i][taxi.sy][taxi.sx];
                    miny =sonnim.get(i).sy;
                    minx = sonnim.get(i).sx;
                    minnum = i;
                }
                else if(cmap[i][taxi.sy][taxi.sx]==minval){
                    if(miny > sonnim.get(i).sy){
                        miny =sonnim.get(i).sy;
                        minx = sonnim.get(i).sx;
                        minnum = i;
                    }else if(miny == sonnim.get(i).sy){
                        if(minx > sonnim.get(i).sx){
                            miny =sonnim.get(i).sy;
                            minx = sonnim.get(i).sx;
                            minnum = i;
                        }
                    }
                }
            }

            // 손님이 있는데 태울수없는경우
            if(minval == Integer.MAX_VALUE){
                System.out.println("-1");
                return;
            }
            // 도착지로 이동할 수 없는경우
            if(emap[minnum] == Integer.MAX_VALUE){
                System.out.println("-1");
                return;
            }

            //3. 골라진 minnum을 데려다주기위한 minval이 현재의 연료보다 많거나 같은지 체크
            if(minval + emap[minnum] <= Fuel){
                // 연료가 가능하다면 해당 연료 계산
                Fuel = (Fuel - minval - emap[minnum])+(emap[minnum] * 2);
                // 택시위치 바꿔주기
                taxi.sy = sonnim.get(minnum).ey;
                taxi.sx = sonnim.get(minnum).ex;

                //데리러갈 사람 sonnim List에서 제거해주기
                //sonnim.remove(minnum);
                sonnimdead[minnum] = true;

            }else{  // 불가능하다면 -1출력하고 프로그램 종료
                System.out.println("-1");
                return;
            }

            sonnimCnt--;
        }
        System.out.println(Fuel);
    }
}
