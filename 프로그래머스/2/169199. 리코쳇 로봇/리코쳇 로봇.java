import java.io.*;
import java.util.*;

class Solution {
    
    static class Node{
        int y;
        int x;
        int time;
        
        Node(int y, int x, int time){
            this.y =y;
            this.x = x;
            this.time = time;
        }
    }
    
    static int yLeng,xLeng;
    // 오, 아, 왼, 위
    static int[] dy = {0,1,0,-1};
    static int[] dx = {1,0,-1,0};
    static char[][] cBoard;
    static boolean[][] visit;
    
    public int solution(String[] board) {
        int answer = -1;
        Queue<Node> que = new ArrayDeque<>();
        
        yLeng = board.length;
        xLeng = board[0].length();
        
        visit = new boolean[yLeng][xLeng];
        cBoard = new char[yLeng][xLeng];
        for(int i=0; i<yLeng; i++){
            for(int j=0; j<xLeng; j++){
                cBoard[i][j] = board[i].charAt(j);
                
                if(cBoard[i][j] == 'R'){
                    que.offer(new Node(i,j,0));
                    visit[i][j]=true;
                }
            }
        }
        
        while(!que.isEmpty()){
            Node now = que.poll();
            
            for(int i=0;i<4;i++){
                Node next = straight(now,i);
                if(next!=null){
                    if(cBoard[next.y][next.x] == 'G'){
                        return next.time;
                    }
                    
                    que.offer(next);
                }
            }
        }
        
        return answer;
    }
    
    // d = 0 ~ 3 (오, 아, 왼, 위)
    public static Node straight(Node now, int d){
        int ny = now.y;
        int nx = now.x;
        
        while(true){
            int wy = ny + dy[d];
            int wx = nx + dx[d];
            
            if(wy <0 || wx <0 || wy >= yLeng || wx >= xLeng || cBoard[wy][wx] == 'D')
                break;
            
            ny = wy;
            nx = wx;
        }
        
        if(!visit[ny][nx]){
            visit[ny][nx] = true;
            return new Node(ny, nx, now.time+1);
        }
        
        return null;
    }
    
    
    
    
}