import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        HashMap<String,Integer> hash = new HashMap<>();
        
        for(int i=0; i<name.length; i++){
            hash.put(name[i],yearning[i]);
        }
        
        for(int i=0; i<photo.length; i++){
            int total = 0;
            for(String pname : photo[i]){
                int num = hash.getOrDefault(pname,0);
                total+=num;
            }
            answer[i] = total;
        }
        
        return answer;
    }
}