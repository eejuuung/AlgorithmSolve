import java.io.*;
import java.util.*;

public class Main {

    public static class Human {
        int money;
        int stock;

        public Human(int money, int stock) {
            this.money = money;
            this.stock = stock;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Human jh = new Human(num, 0);
        Human sm = new Human(num, 0);

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0, upperCount = 0, dropCount = 0, beforeStock = -1; i < 14; i++) {
            num = Integer.parseInt(stz.nextToken());

            while (num <= jh.money) {
                jh.money -= num;
                jh.stock++;
            }
            if (beforeStock < num) {
                upperCount++;
                dropCount = 0;
            }

            if (beforeStock > num) {
                dropCount++;
                upperCount = 0;
            }

            if (upperCount >= 3) {
                sm.money += (sm.stock * num);
                sm.stock = 0;
            }

            if (dropCount >= 3) {
                while (num <= sm.money) {
                    sm.money -= num;
                    sm.stock++;
                }
            }

            beforeStock = num;
        }

        int jhMoney = (jh.money + jh.stock * num);
        int smMoney = (sm.money + sm.stock * num);

        System.out.println((jhMoney > smMoney ? "BNP" : (jhMoney == smMoney ? "SAMESAME" : "TIMING")));

    }
}
