import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        Arrays.sort(arr);

        int minValue = Integer.MAX_VALUE;
        int left = 0;
        int right = N-1;
        StringBuilder sb = new StringBuilder();
        int ans1 = 0;
        int ans2 = 0;

        //양끝에서 탐색 시작
        while (left<right){
            // left와 right의 합의 절대값
            int val = Math.abs(arr[left] + arr[right]);

            // 최소값보다 작다면 갱신
            if(minValue>val){
                minValue = val;
                ans1 = left;
                ans2 = right;

                if(val == 0)
                    break;
            }
            
            //절대값이 큰쪽을 이동
            if(Math.abs(arr[left]) > Math.abs(arr[right]))
                left++;
            else
                right--;
        }

        sb.append(arr[ans1]).append(" ");
        sb.append(arr[ans2]).append("\n");

        System.out.print(sb);
    }
}