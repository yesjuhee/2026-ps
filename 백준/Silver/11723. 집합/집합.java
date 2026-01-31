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
        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            // System.out.println(Integer.toBinaryString(s));
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                int bit = 1 << a;
                s |= bit;
            } else if (cmd.equals("remove")) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                int mask = ~(1 << a);
                s &= mask;
            } else if (cmd.equals("check")) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                int result = (s >> a) & 1;
                sb.append(result).append("\n");
            } else if (cmd.equals("toggle")) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                int bit = 1 << a;
                s ^= bit;
            } else if (cmd.equals("all")) {
                s = 0xfffff;
            } else {
                // clear
                s = 0;
            }
        }
        System.out.println(sb);
	}
}
