import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        String str1 = stz.nextToken();
        String str2 = stz.nextToken();

        if(str1.length()!=str2.length()){
            System.out.println("0");
        }else{
            int cnt = 0;
            for(int i=0;i<str1.length();i++){
                if(str1.charAt(i)== str2.charAt(i)){
                    if(str1.charAt(i)=='8')
                        cnt++;
                } else{
                    break;
                }
            }
            System.out.println(cnt);
        }


    }
}