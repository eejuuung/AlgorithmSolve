import java.io.*;
import java.util.*; 

class Solution {
    public String solution(String s) {
        String answer = "";
        boolean isUpper = true;
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            
            if(c == ' '){
                answer += c;
                isUpper = true;
            }else {
                if(isUpper){
                    answer += Character.toUpperCase(c);
                    isUpper = false;
                } else {
                    answer += Character.toLowerCase(c);
                }
            }
        }
        
        
        
        
        
        return answer;
    }
}