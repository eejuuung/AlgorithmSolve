import java.io.*;
import java.util.*;

class Solution {
    public class Pair{
        int y;
        int x;
        Pair (int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        // 오, 아, 왼, 위
        int[] dy = {0,1,0,-1};
        int[] dx = {1,0,-1,0};
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length(); j++){
                if(!visit[i][j] && maps[i].charAt(j) != 'X'){
                    Queue<Pair> que = new ArrayDeque<>();
                    int total = 0;
                    que.offer(new Pair(i,j));
                    visit[i][j] = true;
                    while(!que.isEmpty()){
                        Pair now = que.poll();
                        total += (maps[now.y].charAt(now.x) - '0');
                        
                        for(int k = 0; k<4; k++){
                            int fy = now.y + dy[k];
                            int fx = now.x + dx[k];
                            
                            if(fy<0 || fx<0 || fy>=maps.length || fx>= maps[fy].length() || visit[fy][fx] || maps[fy].charAt(fx) == 'X')
                                continue;
                            visit[fy][fx] = true;
                            que.offer(new Pair(fy,fx));
                        }
                    }
                    list.add(total);
                }
            }
        }
        answer = new int[list.size()];
        Collections.sort(list);
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return list.size() == 0 ? new int[]{-1} : answer;
    }
}