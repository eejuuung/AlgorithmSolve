class Solution {
    public String solution(String myString, String pat) {
        int idx = -1;
        int last = -1;
        while((idx = myString.indexOf(pat, idx+1)) != -1){
            last = idx;
        }

        return myString.substring(0,last + pat.length());
    }
}