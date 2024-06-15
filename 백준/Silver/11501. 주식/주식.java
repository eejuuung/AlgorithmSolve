import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int rtc = Integer.parseInt(br.readLine());


        for(int tc = 0;tc<rtc;tc++){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer stz = new StringTokenizer(br.readLine());
            boolean[] barr = new boolean[N];
            long[] arr = new long[N];

            for(int i=0;i<N;i++){
                arr[i] = Long.parseLong(stz.nextToken());
            }

            long maxD = arr[N-1];
            for(int i=N-2;i>=0;i--){
                if(arr[i]<maxD){
                    barr[i] = true;
                }
                else if(arr[i]>maxD){
                    maxD = arr[i];
                }
            }




//            for(int i=0;i<N;i++){
//                for(int j=i+1;j<N;j++){
//                    if(arr[i]<arr[j]){
//                        barr[i] = true;
//                        break;
//                    }
//                }
//            }

            // true인 경우 = 이후의 팔것이 있다는 경우에만 살것.
            long totalMoney = 0;
            long nowMoney = 0;
            long nowCount = 0;
            for(int i=0;i<N;i++){

                if(barr[i]){
                    nowMoney-=arr[i];
                    nowCount++;
                }else if(!barr[i] && nowCount!=0){
                    totalMoney += (arr[i] * nowCount) + nowMoney;
                    nowCount = 0;
                    nowMoney = 0;
                }

            }

            sb.append(totalMoney).append("\n");
        }
        System.out.println(sb);
    }
}