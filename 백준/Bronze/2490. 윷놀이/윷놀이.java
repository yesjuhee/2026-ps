import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

	public void solution() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 3; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                if (st.nextToken().equals("0")) cnt++;
            }
            if (cnt == 1) sb.append("A").append("\n");
            else if (cnt == 2) sb.append("B").append("\n");
            else if (cnt == 3) sb.append("C").append("\n");
            else if (cnt == 4) sb.append("D").append("\n");
            else sb.append("E").append("\n");
        }
        System.out.println(sb);
	}
}
