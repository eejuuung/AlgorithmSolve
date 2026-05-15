import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int num : enemy){
            n-=num;
            que.offer(num);
            
            if(n < 0) { 
                if(k > 0) {
                    k--;
                    n += que.poll();
                } else {
                    break;
                }
            }
            answer++;
        }
        
        return answer;
    }
}