import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            pq.add(num);
        }
        int result = 0;
        while(pq.size() != 1) {
            int a = pq.remove();
            int b = pq.remove();
            int sum = a + b;
            result += sum;
            pq.add(sum);
        }
        System.out.println(result);
    }
}
