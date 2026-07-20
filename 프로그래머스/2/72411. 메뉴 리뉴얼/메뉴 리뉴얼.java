import java.io.*;
import java.util.*;

class Solution {
    
    // Orders,Count
    HashMap<String,Integer> hash = new HashMap<>();
    int end = 0;
    int maxCount = 0;
    String str = "";
    
    public void dfs(int depth, String order){
        if(order.length() == end){
            int count = hash.getOrDefault(order,0) +1;
            hash.put(order,count);
            
            maxCount = Math.max(maxCount,count);
            return;
        }
        
        for(int i=depth; i<str.length(); i++){
            dfs(i+1,order + String.valueOf(str.charAt(i)));
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> list = new ArrayList<>();
        
        // order 정렬
        for(int i=0; i<orders.length; i++){
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = new String(arr);
        }
        
        // 조합 + 코스별 최대주문 뽑아내기
        for(int num : course){
            end = num;
            maxCount = 0;
            hash = new HashMap<>();
            
            for(String order : orders){
                str = order;
                dfs(0,"");
            }
            
            for(String key : hash.keySet()){
                int count = hash.getOrDefault(key,0);
                if(count == maxCount && count >= 2){
                    list.add(key);
                }
            }
            
        }
        
        Collections.sort(list);
        answer = new String[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}