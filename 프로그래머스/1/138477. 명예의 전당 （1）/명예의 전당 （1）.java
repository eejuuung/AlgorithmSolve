import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pQue = new PriorityQueue<>();
        
        for(int i=0; i<score.length; i++){
            pQue.offer(score[i]);
            if(i>=k) {   
                pQue.poll();
            }    
            answer[i] = pQue.peek();
        }
        
        return answer;
    }
}