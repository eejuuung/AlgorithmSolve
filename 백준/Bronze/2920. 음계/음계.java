import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        String answer = "mixed";

        boolean updown = true; // true = up, down = false;
        int readD=0;
        int cnt = 0;
        for(;cnt<8;cnt++){
            int parseD = Integer.parseInt(stz.nextToken());
            if(cnt==0){
                if(parseD==8){
                    readD=parseD-1;
                    updown = false;
                }else if(parseD == 1){
                    readD=parseD+1;
                    updown = true;
                }
                else{
                    break;
                }
            }
            else if(parseD == readD){
                readD = (updown?readD+1:readD-1);
            }
            else{
                break;
            }
        }
        if(cnt>=8){
            answer = (updown?"ascending":"descending");
        }
        System.out.println(answer);
    }
}
