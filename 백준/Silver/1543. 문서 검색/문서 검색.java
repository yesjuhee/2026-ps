import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String docs;
    String target;
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        docs = br.readLine();
        target = br.readLine();

        int i = 0;
        int cnt = 0;
        while (i + target.length() <= docs.length()) {
            if (docs.substring(i, i + target.length()).equals(target)) {
                // 같음
                cnt++;
                i += target.length();
            } else {
                // 다름
                i++;
            }
        }

        System.out.println(cnt);
	}
}
