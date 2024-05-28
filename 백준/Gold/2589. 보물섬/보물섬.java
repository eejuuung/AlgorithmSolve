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
        public Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static int R,C;
    public static char[][] cmap;
    public static Queue<Pair> que;
    // 오 아 왼 위
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        cmap = new char[R][C];
        que = new ArrayDeque<>();

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                cmap[i][j] = str.charAt(j);
                if(cmap[i][j] == 'L'){
                    que.add(new Pair(i,j));
                }
            }
        }

        if(que.isEmpty()){
            System.out.println("0");
            return;
        }

        int MaxD = 1;
        int[][] imap;
        Queue<Pair> innerQue;
        while (!que.isEmpty()){
            Pair nowP = que.poll();
            imap = new int[R][C];
            imap[nowP.y][nowP.x] = 1;
            innerQue = new ArrayDeque<>();
            innerQue.offer(nowP);

            while (!innerQue.isEmpty()){
                Pair inoP = innerQue.poll();
                for(int i=0;i<4;i++){
                    int fy =inoP.y + dy[i];
                    int fx = inoP.x + dx[i];

                    if(fy<0 || fx<0 || fy>=R ||fx>=C || imap[fy][fx]!=0 || cmap[fy][fx]!='L')
                        continue;

                    imap[fy][fx] = imap[inoP.y][inoP.x]+1;
                    innerQue.offer(new Pair(fy,fx));
                    if(imap[fy][fx] > MaxD)
                        MaxD = imap[fy][fx];
                }
            }
        }

        System.out.println(MaxD-1);
    }
}