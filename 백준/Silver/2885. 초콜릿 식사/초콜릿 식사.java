import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int i=1;
        while (i<K){
            i=i*2;
        }
        int leng = i;

        int cnt = 0;
        int total = 0;
        while (total<K){

            if(i+total == K){
                break;
            } else if(i + total <K){
                total += i;
            }

            i=i/2;
            cnt++;
        }

        System.out.println(leng + " " + cnt);

    }
}