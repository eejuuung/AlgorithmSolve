class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        boolean[] alpha = new boolean[26];
        
        for(int i=0; i<skip.length(); i++){
            int num = skip.charAt(i) - 'a';
            alpha[num] = true;
        }
        
        for(int i=0; i<s.length(); i++){
            int num = s.charAt(i) - 'a';
            
            for(int k = 0; k < index;){
                num = (num+1) % 26;
                
                if(alpha[num])
                    continue;
                k++;
            }
            answer += (char)('a' + num);
        }
        
        return answer;
    }
}