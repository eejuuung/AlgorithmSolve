import java.io.*;
import java.util.*;

class Solution {
    
    public class Node{
        int progress;
        int speed;
        
        Node(int progress, int speed){
            this.progress = progress;
            this.speed = speed;
        }
    }
    
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Node> que = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        int[] answer = {};
        
        for(int i=0; i<progresses.length; i++){
            que.offer(new Node(progresses[i],speeds[i]));
        }
        
        while(!que.isEmpty()){
            
            for(Node node : que){
                node.progress += node.speed;
            }
            
            if(que.peek().progress < 100)
                continue;
            
            int count = 0;
            
            while(!que.isEmpty() && que.peek().progress >= 100){
                count++;
                que.poll();
            }
            
            if(count != 0)
                list.add(count);
        }
        
        answer = new int[list.size()];
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}