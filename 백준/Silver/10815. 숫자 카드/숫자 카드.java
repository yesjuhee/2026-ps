import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] cards;
    int m;
    StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int q = Integer.parseInt(st.nextToken());
            if (Arrays.binarySearch(cards, q) >= 0) sb.append("1 ");
            else sb.append("0 ");
        }

        System.out.println(sb);
    }
}
