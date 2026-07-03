import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        
        int beforeTime = 1;
        for(int i=0; i<attacks.length; i++){
            
            int time = attacks[i][0];
            int attack = attacks[i][1];
            
            if(time - beforeTime > 0){
                answer += ((time - beforeTime) / bandage[0]) * bandage[2];
                answer += ((time - beforeTime) * bandage[1]);
                
                answer = (answer > health) ? health : answer;
            }
            
            beforeTime = time + 1;
            answer -= attack;
            
            if(answer <= 0)
                return -1;
        }
        return answer;
    }
}