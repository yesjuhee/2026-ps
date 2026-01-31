import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Set<Integer> s = new HashSet<>();
    int m;
    
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
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int a = Integer.parseInt(st.nextToken());
                s.add(a);
            } else if (cmd.equals("remove")) {
                int a = Integer.parseInt(st.nextToken());
                s.remove(a);
            } else if (cmd.equals("check")) {
                int a = Integer.parseInt(st.nextToken());
                if (s.contains(a)) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (cmd.equals("toggle")) {
                int a = Integer.parseInt(st.nextToken());
                if (s.contains(a)) {
                    s.remove(a);
                } else {
                    s.add(a);
                }                
            } else if (cmd.equals("all")) {
                for (int a = 1; a <= 20; a++) {
                    s.add(a);
                }
            } else {
                // empty
                s.clear();
            }
        }
        System.out.println(sb);
	}
}
