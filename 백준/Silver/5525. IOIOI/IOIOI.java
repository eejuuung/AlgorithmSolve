import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int rN = Integer.parseInt(br.readLine());
        int rM = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int totalcnt =0;
        //우선 Pn 만들기
        int pn = (2*rN)+1;
        if (pn <= rM){

            boolean istogle = false;    // false -> I, true -> O
            int cnt = 0;
            for(int i=0;i<rM;i++){
                if(str.charAt(i) == 'I'){
                    if(istogle){
                        if(cnt >=pn){
                            cnt -=pn;
                            int an = cnt/2;
                            totalcnt+= an+1;
                        }
                        cnt = 1;
                    }
                    else{
                        cnt++;
                        istogle = true;
                    }
                }
                else if(str.charAt(i) == 'O'){
                    if(!istogle){
                        if(cnt >=pn){
                            cnt -=pn;
                            int an = cnt/2;
                            totalcnt+= an+1;
                        }
                        cnt = 0;
                    }
                    else{
                        cnt++;
                        istogle = false;
                    }
                }
            }
            //끝나고 남은 cnt 계산
            if(cnt >=pn){
                cnt -=pn;
                int an = cnt/2;
                totalcnt+= an+1;
            }
        }
        System.out.print(totalcnt + "\n");

    }
}
