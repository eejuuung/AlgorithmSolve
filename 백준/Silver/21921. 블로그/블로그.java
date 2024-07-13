import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int X = Integer.parseInt(stz.nextToken());
        int[] arr = new int[N];

        stz = new StringTokenizer(br.readLine());
        int nowNum = 0;
        for(int i=0,j=0;i<N;i++,j++){
            arr[i] = Integer.parseInt(stz.nextToken());

            if(j<X){
                nowNum += arr[i];
            }
        }

        int maxNum = nowNum;
        int count = 1;
        for(int i=X,j=0;i<N;i++,j++){

            nowNum = nowNum - arr[j] + arr[i];
            if(maxNum < nowNum){
                maxNum = nowNum;
                count=1;
            } else if(maxNum == nowNum){
                count++;
            }
        }

        if(maxNum == 0)
            System.out.println("SAD");
        else{
            System.out.print(maxNum + "\n" + count + "\n");
        }


    }
}