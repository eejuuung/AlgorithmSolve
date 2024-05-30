import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean isin = false;

        long G = Long.parseLong(br.readLine());

        long left = 1;
        long right = 2;

        while (right<=100000) {

            if(((right*right) - ((right-1)*(right-1)))> G)
                break;

            if((right * right) - (left * left) < G){
                right++;
                continue;
            }
            else if ((right * right) - (left * left) > G){
                left++;
                continue;
            }
            else if ((right * right) - (left * left) == G){
                isin = true;
                bw.write(String.valueOf(right)+"\n");
                right++;
                continue;
            }
        }


//        for(long i=1, j=0;i<=100000;i++,j++){
//            if(((i*i) - (j*j))> G)
//                break;
//
//            for(long n = 1;n <i;n++){
//
//                if((i*i) - (n*n) == G){
//                    isin = true;
//                    bw.write(String.valueOf(i)+"\n");
//                    break;
//                }
//            }
//        }

        if(isin){
            bw.flush();
        }else{
            bw.write("-1\n");
            bw.flush();
        }
    }
}