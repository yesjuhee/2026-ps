import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] count = new int[9]; // 0 ~ 8 

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            int v = Integer.parseInt(s.substring(i, i+1));
            if (v == 9) {
                count[6]++;
            } else {
                count[v]++;
            }
        }

        count[6] = (count[6] + 1) / 2;

        int max = 0;
        for (int i = 0; i < 9; i++) {
            int v = count[i];
            if (v > max) {
                max = v;
            }
        }

        System.out.println(max);
	}
}
