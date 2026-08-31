import java.io.*;
import java.util.*;

class Solution {
    
    public class Node{
        int weight;
        int time;
        
        Node(int weight, int time){
            this.weight = weight;
            this.time = time;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        Queue<Node> que = new ArrayDeque<>();

        int nowWeight = 0;
        for(int tWeight : truck_weights){
            
            while(nowWeight + tWeight > weight || que.size() >= bridge_length){
                
                for(Node node : que) {
                    node.time += 1;
                }
                answer++;
                if(!que.isEmpty() && que.peek().time >= bridge_length){
                    Node now = que.poll();
                    nowWeight -= now.weight;
                }
            }
            
            que.offer(new Node(tWeight,0));
            nowWeight += tWeight;
        }
        
        while(!que.isEmpty()){
            for(Node node : que) {
                    node.time += 1;
            }
            answer++;
            if(!que.isEmpty() && que.peek().time >= bridge_length){
                Node now = que.poll();
                nowWeight -= now.weight;
            }
        }
        
        return answer;
    }
}