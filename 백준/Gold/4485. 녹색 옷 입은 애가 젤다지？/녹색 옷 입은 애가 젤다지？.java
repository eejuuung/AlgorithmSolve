import java.io.*;
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

    //오, 아, 왼, 위
    public static int[] dy= {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        int N = Integer.parseInt(br.readLine());
        while (N !=0){
            int answer = 0;
            int[][] map = new int[N][N];
            int[][] calmap = new int[N][N];

            for(int i=0;i<N;i++){
                StringTokenizer stz = new StringTokenizer(br.readLine());
                for( int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(stz.nextToken());
                    calmap[i][j] = Integer.MAX_VALUE-20;
                }
            }

            calmap[0][0] = map[0][0];
            Queue<Pair> que = new ArrayDeque<>();
            que.offer(new Pair(0,0));
            while(!que.isEmpty()){
                Pair now = que.poll();

                for(int i=0;i<4;i++){
                    int fy = now.y + dy[i];
                    int fx = now.x + dx[i];

                    if(fy<0 || fx<0 || fy>=N || fx>=N)
                        continue;

                    if(calmap[fy][fx] > calmap[now.y][now.x] + map[fy][fx]){
                        calmap[fy][fx] = calmap[now.y][now.x] + map[fy][fx];
                        que.offer(new Pair(fy,fx));
                    }

                }


            }

            answer =calmap[N-1][N-1];
            sb.append("Problem ").append(tc++).append(": ");
            sb.append(answer).append("\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.print(sb);

    }
}
