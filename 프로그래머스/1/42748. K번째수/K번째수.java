import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int count = 0;
        
        for(int[] command : commands){
            List<Integer> list = new ArrayList<>();
            for(int i = command[0]-1; i<command[1]; i++){
                list.add(array[i]);
            }
            Collections.sort(list);
            answer[count++] = list.get(command[2]-1);
        }
        
        
        
        return answer;
    }
}