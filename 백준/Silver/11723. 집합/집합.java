import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int m;
    // 20개 비트 사용
    int s = 0;
    
	public static void main(String[] args) throws IOException {
        /*
        Set 을 이용해서 풀 수 있음
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int ff = 0;
        for (int i = 1; i <= 20; i++) {
            int bit = 1 << i;
            ff += bit;
        }
        
        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            // System.out.println(Integer.toBinaryString(s));
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int a = Integer.parseInt(st.nextToken());
                int bit = 1 << a;
                s |= bit;
            } else if (cmd.equals("remove")) {
                int a = Integer.parseInt(st.nextToken());
                // 어렵다
                int bit = 1 << a;
                int mask = ff ^ bit; // 11110111
                s &= mask;
            } else if (cmd.equals("check")) {
                int a = Integer.parseInt(st.nextToken());
                int bit = 1 << a;
                if ((s & bit) == bit) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (cmd.equals("toggle")) {
                int a = Integer.parseInt(st.nextToken());
                int bit = 1 << a;
                s ^= bit;
            } else if (cmd.equals("all")) {
                s = ff;
            } else {
                // clear
                s = 0;
            }
        }
        System.out.println(sb);
	}
}
