import java.io.*;
import java.util.*;

class Main {
	
	public static void main(String args[]) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[3];

		arr[0] = Integer.parseInt(st.nextToken());
		arr[1] = Integer.parseInt(st.nextToken());	
		arr[2] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);
		System.out.printf("%d %d %d\n", arr[0], arr[1], arr[2]);
	}
}