import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for(String babble : babbling){
            int length = babble.length();
            int before = 0;
            boolean isOkay = true;
            
            for(int i = 0; i<length; i++){
                if(i+3 <= length && babble.charAt(i) == 'a' && before != 1){
                    String str = babble.substring(i,i+3);
                    if(!str.equals("aya")){
                        isOkay = false;
                        break;
                    }
                    i+=2;
                    before = 1;
                } else if(i+2<= length && babble.charAt(i) == 'y' && before != 2){
                    String str = babble.substring(i,i+2);
                    if(!str.equals("ye")){
                        isOkay = false;
                        break;
                    }
                    i+=1;
                    before = 2;
                } else if(i+3 <= length && babble.charAt(i) == 'w' && before != 3){
                    String str = babble.substring(i,i+3);
                    if(!str.equals("woo")){
                        isOkay = false;
                        break;
                    }
                    i+=2;
                    before = 3;
                } else if(i+2 <= length && babble.charAt(i) == 'm' && before != 4){
                    String str = babble.substring(i,i+2);
                    if(!str.equals("ma")){
                        isOkay = false;
                        break;
                    }
                    i+=1;
                    before = 4;
                } else{
                    isOkay = false;
                    break;
                }
            }
            answer = isOkay ? answer+1 : answer;
        }
        
        
        return answer;
    }
}