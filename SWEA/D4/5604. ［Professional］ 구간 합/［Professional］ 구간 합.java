import java.io.*;
import java.util.*;

public class Solution {

    // 더해지는 값을 체크하기위한 map
    public static HashMap<Long, Long> map;

    public static long Fn(long num) {
        // map에 구해진값이라면 뽑아내서 가져가기
        if (map.containsKey(num))
            return map.get(num);

        // 10이하까지는 미리 구해놓음.
        if (num < 10)
            return map.get(num);

        /*구간합 점화식
        F(n) = F(n-1-n%v)+G(n)
        G(n) = n/v*(n%v+1)+F(n%v)

        ** n = 현재 구하고자 하는 숫자
        ** v = 자릿수
        */

        long v = Fv(num);
        long f = Fn(num - 1 - num % v);
        long g = (num / v) * (num % v + 1) + Fn(num % v);
        long answer = f+g;

        map.put(num,answer);    // 구해진값 map에 넣어놓기 재활용!
        return answer;  // 구해진값 반환
    }

    public static long Fv(long num) {
        // 자릿수구하는 함수
        long v = 1; // 자릿수
        while (num >= 10) { // 10이상의 자릿수체크
            v = v * 10; // v자릿수 증가
            num = num / 10; // num자릿수 나누기
        }
        return v;   // 구해진 자릿수 반환
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        map = new HashMap<>();
        // hashmap에 0~9까지의 합의값을 넣어놓기!
        long sum = 0;
        for (long i = 0; i < 10; i++) {
            sum += i;
            map.put(i, sum);
        }
        int rtc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= rtc; tc++) {
            long answer = 0;
            StringTokenizer stz = new StringTokenizer(br.readLine());
            //a~b까지 입력
            Long a = Long.parseLong(stz.nextToken());
            Long b = Long.parseLong(stz.nextToken());

            // B의 합에서 A의 합 빼주기 a자리까지도 빼줘야하므로 0들어왔을때 예외체크
            if(a==0)
                answer = Fn(b)-Fn(a);
            else
                answer = Fn(b)-Fn(a-1);

            sb.append("#").append(tc).append(" ");
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
