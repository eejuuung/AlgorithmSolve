import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Pair{
        int y;
        int x;
        int cnt;
        Pair(int y, int x,int cnt){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static int N;
    public static int M;
    public static int[][] map;

    // 오, 아, 왼, 위
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static boolean np(int[] p){
        int i = p.length-1;
        while(i>0 && p[i-1] >= p[i])
            i--;

        if(i==0)
            return false;

        int j= p.length-1;
        while (p[i-1]>=p[j])
            j--;
        swap(p,i-1,j);

        j = p.length-1;
        while (i<j)
            swap(p,i++,j--);

        return true;
    }

    public static void swap(int[] p, int a, int b){
        int c = p[a];
        p[a] = p[b];
        p[b] = c;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new int[N][N];
        ArrayList<Pair> virusList = new ArrayList<>();
        int emptyCnt = 0;

        for(int i=0;i<N;i++){
            stz = new StringTokenizer( br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(stz.nextToken());
                if(map[i][j] == 2){
                    virusList.add(new Pair(i,j,1));
                    map[i][j] = 0;
                }
                else if(map[i][j] == 1){
                    map[i][j] = -1;
                    emptyCnt++;
                }
                else
                    emptyCnt++;
            }
        }
        if(virusList.isEmpty()){
            System.out.println("0");
            return;
        }

        int answer = Integer.MAX_VALUE;

        //조합뽑기
        int[] p = new int[virusList.size()];
        for(int i=virusList.size()-1,j=0;j<M;i--,j++){
            p[i] = 1;
        }
        do{
            // 우선 맵 복사
            int[][] copymap = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    copymap[i][j] = map[i][j];
                }
            }

            Queue<Pair> que = new ArrayDeque<>();
            //map 바꿔주고 que에 넣기
            for(int i=0;i< virusList.size();i++){
                if(p[i] == 1){
                    que.offer(new Pair(virusList.get(i).y,virusList.get(i).x,1));
                    copymap[virusList.get(i).y][virusList.get(i).x] = -2;
                }
            }
            while (!que.isEmpty()){
                Pair nowP = que.poll();
                copymap[nowP.y][nowP.x] = nowP.cnt;

                for(int k=0;k<4;k++){
                    int fy = nowP.y + dy[k];
                    int fx = nowP.x + dx[k];

                    if(fy<0 || fx<0 || fy>=N || fx>=N || copymap[fy][fx]!=0)
                        continue;

                    copymap[fy][fx] = nowP.cnt+1;
                    que.offer(new Pair(fy,fx, nowP.cnt+1));
                }
            }
            int totalcnt = 0;
            boolean isout = false;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(copymap[i][j] == 0){
                        isout = true;
                        break;
                    }
                    else if(copymap[i][j]>totalcnt){
                        totalcnt = copymap[i][j];
                    }
                }
                if(isout)
                    break;
            }

            if(!isout){
                if(answer>totalcnt)
                    answer = totalcnt;
            }
        }while (np(p));



        if(answer==Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println(answer-1);
        }
    }
}