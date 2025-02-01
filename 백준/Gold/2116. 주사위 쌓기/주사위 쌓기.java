import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class Dice {
        int[] dice;

        public Dice(int[] dice) {
            this.dice = new int[6];
            System.arraycopy(dice, 0, this.dice, 0, 6);
        }
    }

    public static int N;
    public static Dice[] dice;

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dice = new Dice[N];

        StringTokenizer stz;
        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            int[] newDice = new int[6];
            for (int j = 0; j < 6; j++) {
                newDice[j] = Integer.parseInt(stz.nextToken());
            }
            dice[i] = new Dice(newDice);
        }
    }

    public static int getHighNumber(int a, int b) {
        if (a == 6 || b == 6) {
            return (a == 5 || b == 5) ? 4 : 5;
        }
        return 6;
    }

    public static int[] bottomToTop(int bottomN, int diceN) {

        //0 = topNumber, 1 = HighNumber
        int[] base = new int[2];

        for (int i = 0; i < 6; i++) {
            if (dice[diceN].dice[i] == bottomN) {
                switch (i) {
                    case 0:
                        base[0] = dice[diceN].dice[5];
                        break;
                    case 1:
                        base[0] = dice[diceN].dice[3];
                        break;
                    case 2:
                        base[0] = dice[diceN].dice[4];
                        break;
                    case 3:
                        base[0] = dice[diceN].dice[1];
                        break;
                    case 4:
                        base[0] = dice[diceN].dice[2];
                        break;
                    case 5:
                        base[0] = dice[diceN].dice[0];
                        break;
                }
                base[1] = getHighNumber(base[0], dice[diceN].dice[i]);
                break;
            }
        }
        return base;

    }

    public static int maxDice() {
        int[] base;
        int maxValue = 0;

        // choice To Bottom
        for (int i = 1; i <= 6; i++) {
            base = bottomToTop(i, 0);
            int totalValue = base[1];

            for (int j = 1; j < N; j++) {
                base = bottomToTop(base[0], j);
                totalValue += base[1];
            }

            if (totalValue > maxValue)
                maxValue = totalValue;
        }

        return maxValue;
    }

    public static void main(String[] args) throws Exception {

        init();
        System.out.println(maxDice());

    }
}