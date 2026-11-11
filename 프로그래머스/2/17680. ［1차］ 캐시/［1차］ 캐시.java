import java.io.*;
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        HashSet<String> hset = new HashSet<>();
        List<String> list = new ArrayList<>();
        int count = 0;
        
        if(cacheSize == 0)
            return cities.length * 5;
        
        for(String city : cities){
            city = city.toLowerCase();
            if(count >= cacheSize){
                if(hset.add(city)){
                    hset.remove(list.get(0));
                    list.remove(0);
                    list.add(city);
                    answer+=5;
                }else{
                    answer++;
                    list.remove(city);
                    list.add(city);
                }
            } else {
                if(hset.add(city)){
                    list.add(city);
                    count++;
                    answer+=5;
                } else{
                    answer++;
                    list.remove(city);
                    list.add(city);
                }
            }   
        }
        
        return answer;
    }
}