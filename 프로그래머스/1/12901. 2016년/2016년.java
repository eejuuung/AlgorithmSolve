class Solution {
    public String solution(int a, int b) {
        String answer = "";
        
        int days = b - 1;
        for(int i=1; i<a; i++){
            switch(i){
                case 1,3,5,7,8,10,12 :
                    days += 31;
                    break;
                case 2 :
                    days += 29;
                    break;
                default:
                    days += 30;
                    break;
            }
        }
        
        days %= 7;
        switch (days){
            case 0:
                answer = "FRI";
                break;
            case 1:
                answer = "SAT";
                break;
            case 2:
                answer = "SUN";
                break;
            case 3:
                answer = "MON";
                break;
            case 4:
                answer = "TUE";
                break;
            case 5:
                answer = "WED";
                break;
            case 6:
                answer = "THU";
                break;
        }
        
        return answer;
    }
}