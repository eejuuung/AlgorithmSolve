class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            int num;
            if(c >= 'a' && c <= 'z'){
                num = (c - 'a' + n) % 26 + 'a';
            } else if(c >= 'A' && c <='Z'){
                num = (c - 'A' + n) % 26 + 'A';
            } else {
                num = c;
            }
            answer += (char)num;
        }
        
        return answer;
    }
}