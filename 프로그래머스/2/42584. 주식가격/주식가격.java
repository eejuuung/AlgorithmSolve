import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int leng = prices.length;
        int[] answer = new int[leng];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        for(int i=1; i<leng; i++){
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]){
                int num = stack.pop();
                answer[num] = i - num;
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            int num = stack.pop();
            answer[num] = leng - 1 - num;
        }
        
        return answer;
    }
}