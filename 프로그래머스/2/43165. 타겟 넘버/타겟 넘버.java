import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        Queue<Integer> que = new ArrayDeque<>();
        que.add(numbers[0]);
        que.add(numbers[0]*-1);
        
        for(int i=1;i<numbers.length;i++){
            int size = que.size();
            for(int j=0;j<size;j++){
                int num = que.poll();
                que.add(num + numbers[i]);
                que.add(num - numbers[i]);
            }
        }
        
        while(!que.isEmpty()){
            int num = que.poll();
            if(num == target)
                answer++;
        }
        
        return answer;
    }
}