import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Pair{
        int y;
        int x;
        int key;
        int cnt;
        Pair(int y,int x,int key, int cnt){
            this.y = y;
            this.x = x;
            this.key = key;
            this.cnt = cnt;
        }
    }

    public static int R,C;
    public static char[][] map;
    public static boolean[][][] bmap;
    public static boolean[] keymap;

    //상, 우, 하, 좌
    public static int[] dy = {-1,0,1,0};
    public static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());

        map = new char[R][C];
        bmap = new boolean[64][R][C];
        keymap = new boolean[6];
        Queue<Pair> que = new ArrayDeque<>();
        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]  == '0'){
                    que.offer(new Pair(i,j,0,1));
                }
            }
        }
        
        int answer = -1;
        while (que!=null && !que.isEmpty()){
            Pair minsik = que.poll();

            for(int k=0;k<4;k++){
                int fy = minsik.y + dy[k];
                int fx = minsik.x + dx[k];
                int fkey = minsik.key;

                //범위를 벗어나거나, 갔던곳이거나, 벽이라면 가지않음.
                if(fy<0 || fx<0 || fy>=R || fx>= C || bmap[fkey][fy][fx] || map[fy][fx] == '#')
                    continue;
                
                //방문체크
                bmap[fkey][fy][fx] = true;
                // 탈출구임.
                if(map[fy][fx] == '1'){
                    answer = minsik.cnt;
                    que = null;
                    break;
                }
                //key먹었음!
                else if(map[fy][fx]>='a' && map[fy][fx]<='f'){
                    int cal = map[fy][fx] - 'a';
                    cal = (1<<cal);
                    fkey = (fkey | cal);
                    que.offer(new Pair(fy,fx,fkey, minsik.cnt+1));
                }
                // 문임.
                else if(map[fy][fx]>='A' && map[fy][fx]<='F'){
                    //갈수 잇는 위치인지체크
                    int cal = map[fy][fx] - 'A';
                    cal = (1<<cal);
                    if((fkey & cal)!=0){
                        que.offer(new Pair(fy,fx,fkey, minsik.cnt+1));
                    }
                }
                else{
                    que.offer(new Pair(fy,fx,fkey, minsik.cnt+1));
                }
            }
        }

        System.out.println(answer);
    }
}
