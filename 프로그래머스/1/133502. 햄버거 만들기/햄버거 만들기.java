import java.io.*;
import java.util.*;

class Solution {
    public class Pair{
        int num;
        int level;
        
        public Pair(int num, int level){
            this.num = num;
            this.level = level;
        }
    }
    
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Pair> stack = new Stack<>();
        int roop = 0;
        int roopNum = 0;
        
        int i=0;
        for(int num : ingredient){
            if(roop > 0){
                if(roopNum == 1 && num == 2 && roop == 1){
                    roop = 2;
                }else if(roopNum == 2 && num == 3 && roop == 2){
                    roop = 3;
                }else if(roopNum == 3 && num == 1 && roop == 3){
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    if(!stack.isEmpty()){
                        roopNum = stack.peek().num;
                        roop = stack.peek().level;
                    }
                    else {
                        roopNum = 0;
                        roop = 0;
                    }
                    
                    answer++;
                    continue;
                } else{
                    if(num == 1)
                        roop = 1;
                    else
                        roop = 0;
                }
            } else{
                if(num == 1)
                    roop = 1;
            }
            
            roopNum = num;
            stack.push(new Pair(num,roop));
        }
        
        return answer;
    }
}