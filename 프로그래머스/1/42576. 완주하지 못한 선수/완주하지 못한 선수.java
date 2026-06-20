import java.io.*;
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        HashMap<String,Integer> hash = new HashMap<>();
        for(String key : participant){
            hash.put(key, hash.getOrDefault(key,0)+1);
        }
        
        for(String key : completion){
            int num = hash.get(key);
            
            if(num == 1) {
                hash.remove(key);
            } else{
                hash.replace(key, num-1);
            }
        }
        
        return hash.keySet().iterator().next();
    }
}