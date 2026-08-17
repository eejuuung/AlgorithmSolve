import java.io.*;
import java.util.*;

class Solution {
    
    public class Node{
        int y;
        int x;
        int n;
        Node(int y, int x, int n){
            this.y = y;
            this.x = x;
            this.n = n;
        }
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        // 오, 아 왼, 위
        int[] dy = {0,1,0,-1};
        int[] dx = {1,0,-1,0};
        
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(0,0,1));
        maps[0][0] = 0;
        
        while(!que.isEmpty()){
            Node now = que.poll();
            
            if(now.y == n-1 && now.x == m-1){
                return now.n;
            }
            
            for(int i=0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                
                if(y < 0 || x < 0 || y >= n || x >= m || maps[y][x] !=1){
                    continue;
                }
                
                que.offer(new Node(y,x,now.n+1));
                maps[y][x] = 0;
            }
        }
        
        return -1;
    }
}