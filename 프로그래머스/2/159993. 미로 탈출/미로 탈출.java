import java.io.*;
import java.util.*;

class Solution {
    public class Pair{
        int y;
        int x;
        int time;
        public Pair(int y, int x, int time){
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
    
    int N = 0;
    int M = 0;
    int[][] map;
    
    public int calTime(Pair start, Pair goal){
        // 오, 아, 왼, 위
        int[] dy = {0,1,0,-1};
        int[] dx = {1,0,-1,0};
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(start);
        boolean[][] visit = new boolean[N][M];
        visit[start.y][start.x] = true;
        
        while(!que.isEmpty()){
            Pair now = que.poll();
            if(now.y == goal.y && now.x == goal.x){
                return now.time;
            }
            
            for(int i=0; i<4; i++){
                int fy = now.y + dy[i];
                int fx = now.x + dx[i];
                
                if(fy<0 || fx<0 || fy>=N || fx>=M || 
                   map[fy][fx] == -1 || visit[fy][fx])
                    continue;
                
                if(fy == goal.y && fx == goal.x)
                    return now.time+1;
                
                que.offer(new Pair(fy,fx,now.time+1));
                visit[fy][fx] = true;
            }
        }
        return -1;
    } 
    
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        Pair start = new Pair(0,0,0);
        Pair end = new Pair(0,0,0);
        Pair lever = new Pair(0,0,0);
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                switch (maps[i].charAt(j)){
                    case 'S':
                        start = new Pair(i,j,0);
                        break;
                    case 'E':
                        end = new Pair(i,j,0);
                        break;
                    case 'L':
                        lever = new Pair(i,j,0);
                        break;
                    case 'X':
                        map[i][j] = -1;
                        break;
                }
            }
        }
        
        int time = calTime(start,lever);
        if(time == -1)
            return -1;
        
        answer = time;
        time = calTime(lever,end);
        
        return (time==-1) ? -1 : answer+time;
    }
}