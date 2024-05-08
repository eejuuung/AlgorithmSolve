import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class State{
        int y;
        int x;

        public State(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static int R,C,N;
    public static char[][] map;
    public static int[][] imap;

    // 오, 아, 왼, 위
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(stz.nextToken());    // 세로
        C = Integer.parseInt(stz.nextToken());    // 가로
        N = Integer.parseInt(stz.nextToken());    // 초

        map = new char[R][C];
        imap = new int[R][C];

        for(int i=0;i<R;i++) {
            String str = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'O') {
                    imap[i][j] = 3;
                }
            }
        }

        int nowTime = 2;
        while(nowTime<=N) {
            //홀수초 = 아무것도 하지 않는다. (폭탄 터지는지 체크)
            if(nowTime%2!=0){
                Queue<State> que = new ArrayDeque<>();
                for(int i=0;i<R;i++){
                    for(int j=0;j<C;j++){
                        if(imap[i][j] !=0 && imap[i][j] <= nowTime ){
                            imap[i][j] = 0;
                            map[i][j] = '.';
                            que.offer(new State(i,j));
                        }
                    }
                }
                while (!que.isEmpty()){
                    State nowState = que.poll();
                    for(int k=0;k<4;k++) {
                        int fy = nowState.y + dy[k];
                        int fx = nowState.x + dx[k];

                        if(fx<0 || fy<0 || fy>=R || fx>=C)
                            continue;

                        map[fy][fx] = '.';
                        imap[fy][fx] = 0;
                    }
                }

            }else{ // 폭탄이 없는위치에 폭탄을 설치한다.
                for(int i=0;i<R;i++) {
                    for(int j=0;j<C;j++) {
                        if(map[i][j] == '.') {
                            map[i][j] = 'O';
                            imap[i][j] = nowTime+3;
                        }
                    }
                }
            }

            nowTime++;
        }
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                bw.write(map[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
    }

}