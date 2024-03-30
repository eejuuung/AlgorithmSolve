import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int start = 0;
        int cnt0 = (str.charAt(0)=='0'?1:0);
        int end = start;
        int cnt1 = (str.charAt(0)=='1'?1:0);

        for (;end < str.length();end++) {
            if(str.charAt(start) != str.charAt(end)){
                start = end;
                cnt0 = (str.charAt(end)=='0'?++cnt0:cnt0);
                cnt1 = (str.charAt(end)=='1'?++cnt1:cnt1);
            }
        }

        int answer = Integer.min(cnt0,cnt1);
        System.out.println(answer);

    }
}
