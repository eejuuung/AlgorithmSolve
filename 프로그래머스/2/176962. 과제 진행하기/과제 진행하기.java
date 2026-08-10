import java.io.*;
import java.util.*;

class Solution {
    
    public int[] toTime(String str){
        int[] time = new int[2];
        String[] arr = str.split(":");
        time[0] = Integer.parseInt(arr[0]);
        time[1] = Integer.parseInt(arr[1]);
        
        return time;
    }
    
    public int[] pTime(int[] time, int playtime){
        time[0] += (time[1] + playtime) / 60;
        time[1] = (time[1] + playtime) % 60;
        
        return time;
    }
    
    public int compareTime(int[] time1, int[] time2){
        return (time2[0]*60 + time2[1]) - (time1[0]*60 + time1[1]);
    }
    
    public String[] solution(String[][] plans) {
        int leng = plans.length;
        int count = 0;
        String[] answer = new String[leng];
        Stack<Integer> sta = new Stack<>();
        
        Arrays.sort(plans, (a,b) -> a[1].compareTo(b[1]));
        sta.push(0);
        
        int[] now = toTime(plans[sta.peek()][1]);
        for(int i=1; i<leng; i++){
            int[] start = toTime(plans[i][1]);
            
            if(sta.isEmpty()){
                sta.push(i);
                now = start;
                continue;
            }
            
            while(!sta.isEmpty ()){
                int time = compareTime(now, start);
                
                int idx = sta.peek();
                int playtime = Integer.parseInt(plans[idx][2]);
                
                if(time >= playtime){
                    int num = sta.pop();
                    answer[count++] = plans[num][0];
                    now = pTime(now,playtime);
                } else {
                    plans[idx][2] = String.valueOf(playtime - time);
                    sta.push(i);
                    now = start;
                    break;
                }       
            }
            
            if(sta.isEmpty()){
                sta.push(i);
                now = start;
            }
        }
        
        while(!sta.isEmpty()){
            int num = sta.pop();
            answer[count++] = plans[num][0];
        }
        
        return answer;
    }
}