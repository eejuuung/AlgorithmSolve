import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        int[] map = new int[N+1];
        int[] arr = new int[100001];

        stz = new StringTokenizer(br.readLine());
        int maxCount = 0;
        int nowCount = 0;

        for(int i=0,j=0;i<N;i++){
            int rnum = Integer.parseInt(stz.nextToken());
            map[i] = rnum;
            arr[rnum]++;

            //수열 안되는 경우 여기까지만 체크하고 다시 시작해야함.
            if(arr[rnum]>K){

                while(true){
                    arr[map[j]]--;
                    if(map[j]==rnum){
                        j++;
                        //여기까지가 빼야되는 값
                        break;
                    }
                    j++;
                    nowCount--;
                }
                //이후부터 다시 증가시키면 됨

            } else{  // 되는경우
                nowCount++;

                if(maxCount<nowCount){
                    maxCount = nowCount;
                }
            }
        }
        System.out.println(maxCount);
    }
}