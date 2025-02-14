import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * HashMap은 동일한 Key를 허용하지 않으므로 하나의 Key에 여러개의 값을 저장하려면 List 또는 Set 구조를 사용해야함.
 */
public class Main {

    public static StringBuilder sb;
    public static HashMap<String, Integer> hMap;
    public static int friendCount;
    public static int[] fCount;

    public static void friendNetwork(String person1, String person2) {

        if (hMap.containsKey(person1) && hMap.containsKey(person2)) {
            int changeV = hMap.get(person1);
            int getV = hMap.get(person2);

            if (changeV != getV) {
                hMap.replaceAll((key, value) -> value == getV ? changeV : value);
                fCount[changeV] += fCount[getV];
                fCount[getV] = 0;
            }
            sb.append(fCount[changeV]).append("\n");
        } else if (hMap.containsKey(person1)) {
            int changeV = hMap.get(person1);
            hMap.put(person2, changeV);
            fCount[changeV]++;
            sb.append(fCount[changeV]).append("\n");
        } else if (hMap.containsKey(person2)) {
            int changeV = hMap.get(person2);
            hMap.put(person1, changeV);
            fCount[changeV]++;
            sb.append(fCount[changeV]).append("\n");
        } else {
            hMap.put(person1, friendCount);
            hMap.put(person2, friendCount);
            fCount[friendCount] += 2;
            sb.append(fCount[friendCount++]).append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        sb = new StringBuilder();

        for (int i = 0; i < tc; i++) {
            int cnt = Integer.parseInt(br.readLine());
            hMap = new HashMap<>();
            friendCount = 0;
            fCount = new int[200005];

            for (int j = 0; j < cnt; j++) {
                stz = new StringTokenizer(br.readLine());
                friendNetwork(stz.nextToken(), stz.nextToken());
            }
        }
        System.out.print(sb);
    }

}