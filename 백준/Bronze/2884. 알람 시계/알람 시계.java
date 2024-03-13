import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int H = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());

        if(M<45){
            H-=1;
            M = M+15; // 60-45
            if(H<0){
                H = 23;
            }
        }else{
            M -=45;
        }

        sb.append(H).append(" ").append(M).append("\n");
        System.out.print(sb);
    }
}
