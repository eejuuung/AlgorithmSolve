import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static HashMap<Integer, String> dictionary;
    public static StringBuilder sb;

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dictionary = new HashMap<>();
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            if (!dictionary.containsValue(str)) {
                dictionary.put(dictionary.size() + 1, str);
            }
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            str = br.readLine();
            autoCorrection(str);
        }
    }

    public static void autoCorrection(String str) {
        if (dictionary.containsValue(str)) {
            sb.append(str).append(" is correct").append("\n");
            return;
        } else {
            for (int i = 1; i <= dictionary.size(); i++) {
                String diction = dictionary.get(i);

                // 한글자 잘못 적거나 인접한 두 글자의 순서가 잘못된 경우
                int abs = Math.abs(str.length() - diction.length());
                if (abs == 0) {
                    boolean nearMiss = false;
                    int missCount = 0;

                    for (int j = 0; j < str.length(); ) {
                        if (str.charAt(j) != diction.charAt(j)) {
                            if (j + 1 < str.length()) {
                                if (str.charAt(j) == diction.charAt(j + 1) && str.charAt(j + 1) == diction.charAt(j)) {
                                    j += 2;
                                    missCount += 2;
                                    nearMiss = true;
                                    continue;
                                }
                            }
                            missCount++;
                        }
                        j++;
                    }

                    if (missCount == 1 || (missCount == 2 && nearMiss)) {
                        sb.append(str).append(" is a misspelling of ").append(diction).append("\n");
                        return;
                    }
                }
                // 한글자 적게쓰거나 한글자 많이 쓴 경우
                else if (abs <= 1) {
                    String smallStr = (str.length() < diction.length() ? str : diction);
                    String largeStr = (str.length() > diction.length() ? str : diction);
                    boolean everCorrect = false;
                    boolean isnotCorrect = false;
                    int correctCount = 0;
                    for (int n = 0, m = 0; n < smallStr.length() && m < largeStr.length(); ) {

                        if (smallStr.charAt(n) != largeStr.charAt(m)) {
                            if (isnotCorrect) {
                                everCorrect = true;
                                break;
                            } else {
                                m++;
                                isnotCorrect = true;
                            }
                        } else {
                            n++;
                            m++;
                            correctCount++;
                        }
                    }

                    if (!everCorrect && correctCount == smallStr.length()) {
                        sb.append(str).append(" is a misspelling of ").append(diction).append("\n");
                        return;
                    }
                }
            }
        }

        sb.append(str).append(" is unknown").append("\n");
    }

    public static void main(String[] args) throws Exception {
        init();
        System.out.print(sb);
    }
}