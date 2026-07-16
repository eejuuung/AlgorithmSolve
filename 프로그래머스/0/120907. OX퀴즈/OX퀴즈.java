import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        int count = 0;
        
        for(String str : quiz){
            String[] sArr = str.split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[2]);
            int c = Integer.parseInt(sArr[4]);
            
            if(sArr[1].equals("+")){
                answer[count++] = (a+b==c?"O":"X");
            } else{
                answer[count++] = (a-b==c?"O":"X");
            }
            
        }
        
        
        
        return answer;
    }
}