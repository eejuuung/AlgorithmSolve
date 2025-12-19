import java.io.*;
import java.util.*;

public class Main {

    public static class Beer implements Comparable<Beer> {
        int love;
        int damage;

        public Beer(int love, int damage) {
            this.love = love;
            this.damage = damage;
        }

        @Override
        public int compareTo(Beer o) {
            if (this.damage != o.damage) {
                return this.damage - o.damage;
            }
            return o.love - this.love;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stz.nextToken());
        int M = Integer.parseInt(stz.nextToken());
        int K = Integer.parseInt(stz.nextToken());
        Queue<Beer> que = new PriorityQueue<Beer>((o1, o2) -> {
            if (o1.damage != o2.damage) {
                return o1.damage - o2.damage;
            }
            return o2.love - o1.love;
        });

        for (int i = 0; i < K; i++) {
            stz = new StringTokenizer(br.readLine());
            int love = Integer.parseInt(stz.nextToken());
            int damage = Integer.parseInt(stz.nextToken());
            que.offer(new Beer(love, damage));
        }

        Queue<Beer> drinkBeer = new PriorityQueue<Beer>((o1, o2) -> {
            if (o1.love != o2.love) {
                return o1.love - o2.love;
            }
            return o2.damage - o1.damage;
        });
        int drinkCount = 0;
        Beer jiyoung = new Beer(0, 0);

        for (int i = 0; i < K; i++) {
            Beer nowB = que.poll();

            if (drinkCount >= N) {
                if (jiyoung.love >= M)
                    break;

                Beer outBeer = drinkBeer.poll();
                if (outBeer.love >= nowB.love) {
                    drinkBeer.offer(outBeer);
                    continue;
                } else {
                    jiyoung.love -= outBeer.love;
                }
            }

            drinkBeer.offer(nowB);

            if (nowB.damage > jiyoung.damage)
                jiyoung.damage = nowB.damage;
            jiyoung.love += nowB.love;
            drinkCount++;

        }

        System.out.println((jiyoung.love < M ? "-1" : jiyoung.damage));
    }
}