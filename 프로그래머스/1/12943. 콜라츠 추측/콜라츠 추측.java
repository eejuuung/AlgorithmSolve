class Solution {
    public int solution(int num) {
        int answer = 0;
        long n = (long)num;
        
        while(n != 1 && answer < 500){
            answer++;
            
            if(n%2==0){
                n/=2;
            } else{
                n = (n*3)+1;
            }
        }
        
        return n == 1 ? answer : -1;
    }
}