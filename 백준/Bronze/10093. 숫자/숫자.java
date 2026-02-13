import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long a, b;

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        long big = a > b ? a : b;
        long small = a < b ? a : b;

        if (a == b) System.out.println(0);
        else System.out.println(big - small - 1);

        StringBuilder sb = new StringBuilder();
        for (long i = small + 1; i < big; i ++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        System.out.println(sb);
    }
}
