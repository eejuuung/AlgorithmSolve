import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class dungeon{
        long stage; //방의 상황
        long power; // 공격력
        long hp; // 생명력

        dungeon(long stage, long power, long hp){
            this.stage = stage;
            this.power = power;
            this.hp = hp;
        }
    }

    public static long N;    // 던전갯수
    public static long Hatk; // 총 체력
    public static dungeon[] map;

    public static boolean doing(long hp){
        long power = Hatk;  // 현재 공격력
        long curHp = hp;    // 현재 체력

        for(int i=0;i<N;i++){
            long stage = map[i].stage;

            if(stage == 1){ // 몬스터
                long monPower = map[i].power;   // 몬스터 공격력
                long monHp = map[i].hp; // 몬스터 체력

                long time = 0;  // 걸리는시간
                if(monHp % power == 0)  // 내 공격력으로 몬스터를 잡는데 걸리는 시간 계산
                    time = monHp / power -1;
                else
                    time = monHp / power;

                curHp -= (monPower * time); // 현재 체력에서 몬스터의 공격력에 내가 걸리는 시간을 곱해서 체력이 괜찮은지 체크

                if(curHp <= 0)  //현재체력이 더 작아진다면 안되는 경우
                    return false;
            }
            else{ // 포션방
                power += map[i].power;  // 공격력증가
                curHp = Math.min(hp, curHp + map[i].hp);    // 체력증가
            }

        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        Hatk = Integer.parseInt(stz.nextToken());
        map = new dungeon[(int)N];

        for(int i=0;i<N;i++){
            stz = new StringTokenizer(br.readLine());
            int stage = Integer.parseInt(stz.nextToken());
            int power = Integer.parseInt(stz.nextToken());
            int hp = Integer.parseInt(stz.nextToken());
            map[i] = new dungeon(stage,power,hp);
        }
        long ans = 0;
        long left = 0;
        long right = Long.MAX_VALUE-1;
        long mid;

        while (left<=right){    // 이분탐색 왼쪽과 오른쪽에서 가운데를 잡고 이동하면서 두탐색점 넘어가면 멈춤
            mid = (left + right) /2;    // 중간잡기

            if(doing(mid)){ // 되는경우일때는 체력 줄여서 진행
                right = mid-1;
                ans = mid;
            }
            else{   // 안되는 경우일때는 체력 늘려서 진행
                left = mid+1;
            }
        }
        System.out.println(ans);
    }
}