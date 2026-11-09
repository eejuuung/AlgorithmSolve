class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int[] left = new int[10001];
        int[] right = new int[10001];
        int leftTotal = 1;
        int rightTotal = 0;
        left[topping[0]]++;
        
        for(int i = 1; i<topping.length; i++){
            if(right[topping[i]] == 0)
                rightTotal++;
            right[topping[i]]++;
        }
        if(rightTotal == leftTotal)
            answer++;
        
        
        for(int i=1; i<topping.length-1; i++){
            if(right[topping[i]] == 1)
                rightTotal--;
            right[topping[i]]--;
            
            if(left[topping[i]] == 0)
                leftTotal++;
            left[topping[i]]++;
            
            
            if(leftTotal == rightTotal)
                answer++;
        }
        
        return answer;
    }
}