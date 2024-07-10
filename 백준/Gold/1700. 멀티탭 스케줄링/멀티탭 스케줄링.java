import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        int[] arr = new int[K];
        int[] multitab = new int[N];
        int answer = 0;

        stz = new StringTokenizer(br.readLine());
        // 우선 입력 리스트 정리
        for(int i=0;i<K;i++){
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        for(int i=0;i<K;i++){

            boolean isFull = true;
            for(int j=0;j<N;j++){
                // 1. 비어있으면 넣기
                if(multitab[j] == 0){
                    multitab[j] = arr[i];
                    isFull = false;
                    break;
                }
                // 2. 만약 같은 번호가 들어가 있으면 넘어가기
                else if(multitab[j] == arr[i]){
                    isFull = false;
                    break;
                }
            }
            // 3. 만약 가득 차있다면
            if(isFull){
                // 3-1. 뒤에 남은 목록 우선순위 담기
                int[] map = new int[K+1];
                for(int j=K-1, k=1;j>=i;j--,k++){
                    map[arr[j]] = k;
                }
                // 3-2. 우선순위가 낮을 수록 뽑아내야함.
                int minj = 0;
                int mind = Integer.MAX_VALUE;
                for(int j=0;j<N;j++){
                    if(map[multitab[j]]<mind){
                        mind = map[multitab[j]];
                        minj = j;
                    }
                }
                multitab[minj] = arr[i];
                answer++;
            }
        }

        System.out.println(answer);
    }
}