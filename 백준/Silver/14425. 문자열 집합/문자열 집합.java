import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    Set<String> strSet = new HashSet<>();
    int cnt = 0;

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            strSet.add(br.readLine());    
        }

        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            if (strSet.contains(str)) cnt++;
        }

        System.out.println(cnt);
    }
}