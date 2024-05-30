import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean isin = false;

        long G = Long.parseLong(br.readLine());

        for(long i=1, j=0;i<=100000;i++,j++){
            if(((i*i) - (j*j))> G)
                break;

            for(long n = 1;n <i;n++){

                if((i*i) - (n*n) == G){
                    isin = true;
                    bw.write(String.valueOf(i)+"\n");
                    break;
                }
            }
        }
        if(isin){
            bw.flush();
        }else{
            bw.write("-1\n");
            bw.flush();
        }


    }
}