import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    String[] pattern;
    StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        pattern = br.readLine().split("\\*");

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if(str.startsWith(pattern[0]) 
               && str.endsWith(pattern[1])
               && str.length() >= pattern[0].length() + pattern[1].length()
              ) {
                sb.append("DA\n");
            } else {
                sb.append("NE\n");
            }
        }

        System.out.println(sb);
	}
}
