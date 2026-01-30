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

        int s1 = docs.length();
        docs = docs.replace(target, "");
        int s2 = docs.length();
        int diff = s1 - s2;

        System.out.println(diff / target.length());
	}
}
