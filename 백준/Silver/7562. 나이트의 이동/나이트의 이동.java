import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Pair{
        int y;
        int x;
        int time;
        public Pair(int y, int x,int time){
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    //왼위 오위 오아 왼아
    public static int[] dy = {-1,-2,-2,-1,1,2,2,1};
    public static int[] dx = {-2,-1,1,2,2,1,-1,-2};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int rtc = 0;rtc<tc;rtc++){
            int N = Integer.parseInt(br.readLine());
            boolean[][] bmap = new boolean[N][N];

            StringTokenizer stz = new StringTokenizer(br.readLine());
            Pair start = new Pair(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()),0);
            stz = new StringTokenizer(br.readLine());
            Pair end = new Pair(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()),0);

            Queue<Pair> que = new ArrayDeque<>();
            que.offer(start);
            bmap[start.y][start.x] = true;

            while (!que.isEmpty()){
                Pair nowP = que.poll();

                if(nowP.y == end.y && nowP.x == end.x){
                    sb.append(nowP.time).append("\n");
                    break;
                }

                for(int i=0;i<8;i++){
                    int fy = nowP.y + dy[i];
                    int fx = nowP.x + dx[i];

                    if(fy<0 || fx<0 || fy>=N || fx>=N || bmap[fy][fx]){
                        continue;
                    }

                    que.offer(new Pair(fy,fx, nowP.time+1));
                    bmap[fy][fx] = true;

                }

            }

        }

        System.out.print(sb);

    }
}