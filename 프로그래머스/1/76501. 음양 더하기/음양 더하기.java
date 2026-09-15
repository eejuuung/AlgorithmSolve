class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = absolutes[0];
        if(!signs[0])
            answer = (answer !=0) ? answer*=-1 : answer;
        
        for(int i = 1; i<absolutes.length; i++){
            if(!signs[i])
                absolutes[i] = (absolutes[i] !=0) ? absolutes[i]*=-1 : absolutes[i];
            
            answer += absolutes[i];
        }
        
        return answer;
    }
}