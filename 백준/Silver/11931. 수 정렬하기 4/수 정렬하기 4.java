import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

	public void solution() throws IOException {
        int n;
        int[] arr;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for(int i = n - 1; i >= 0; i--){
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
	}
}
