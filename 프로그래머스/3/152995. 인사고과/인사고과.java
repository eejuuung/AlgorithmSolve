import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int[] wonho = new int[2];
        wonho[0] = scores[0][0];
        wonho[1] = scores[0][1];
        int wonhoTotal = wonho[0] + wonho[1];
        
        Arrays.sort(scores, (a,b)->{
            if(a[0]!=b[0]){
                return Integer.compare(b[0],a[0]);
            }
            return Integer.compare(a[1],b[1]);
        });
        
        int maxB = -1;
        
        for(int[] arr: scores){
            int a = arr[0], b = arr[1];
            
            if(b<maxB){
                if(a == wonho[0] && b == wonho[1]){
                    answer = -1;
                    break;
                }
                continue;
            }
            
            if(a+b>wonhoTotal) answer++;
            
            if(b>maxB)
                maxB = b;
            
        }
        
        return answer;
    }
}