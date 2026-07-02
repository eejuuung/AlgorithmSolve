import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
            
        for(int i=0; i<n; i++) {
            
            boolean success = true;
            int nowday = startday;
                
            int hour = schedules[i] / 100;
            int minute = (schedules[i] % 100) + 10;
            if(minute >= 60) {
                hour++;
                minute -=60;
            }
            int time = (hour*100) + minute;
            
            for(int j=0; j<7; j++){
                if(nowday !=6 && nowday!=7){
                    if(timelogs[i][j] > time){
                        success = false;
                        break;
                    }
                }
                nowday = (nowday%7)+1;
            }
            
            if(success)
                answer++;
        }

        return answer;
    }
}