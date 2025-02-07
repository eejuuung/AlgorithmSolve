import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static String getIp(String str) {
        StringBuilder returnStr = new StringBuilder();
        String[] getIPv6 = str.split(":");
        for (int i = 0; i < getIPv6.length; i++) {
            if (getIPv6[i].length() < 4) {
                for (int j = 0; j < 4 - getIPv6[i].length(); j++) {
                    returnStr.append("0");
                }
            }
            returnStr.append(getIPv6[i]).append(":");
        }

        return returnStr.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ipv6 = br.readLine();
        StringBuilder sb = new StringBuilder();

        int twoColon = ipv6.indexOf("::");

        // 축약 없음.
        if (twoColon == -1) {
            String ip = getIp(ipv6);
            sb.append(ip.substring(0, ip.length() - 1));
        } else {
            //:: 앞 뒤 데이터 정리하기
            String strFront = "";
            String strMiddle = "";
            String strBack = "";
            int madeCount = 0;

            // Front
            if (twoColon != 0) {
                strFront = getIp(ipv6.substring(0, twoColon));
                madeCount += strFront.length();
            }

            // Back
            if (twoColon + 2 != ipv6.length()) {
                strBack = getIp(ipv6.substring(twoColon + 2));
                madeCount += strBack.length();
            }

            // :: madeCount =  40
            for (int i = madeCount; i < 40; i += 5) {
                strMiddle += "0000:";
            }

            String ip = strFront + strMiddle + strBack;
            sb.append(ip.substring(0, ip.length() - 1));
        }

        sb.append("\n");
        System.out.print(sb);
    }
}