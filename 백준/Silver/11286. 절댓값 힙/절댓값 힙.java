import java.io.*;
import java.util.*;

public class Main {

	private int n;
	private PriorityQueue<Node> pq = new PriorityQueue<>();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	static class Node implements Comparable<Node> {

		int v; // 원래 값
		int abs; // 절대 값

		public Node(int v) {
			this.v = v;
			this.abs = v < 0 ? v * -1: v;
		}

		@Override
		public int compareTo(Node o) {
			if (this.abs == o.abs) {
				return this.v - o.v;
			}
			return this.abs - o.abs;
		}
	}

	private void solution() throws IOException {
		// 입력
		n = Integer.parseInt(br.readLine());
		var sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());

			// 0이 아닌 경우
			if (input != 0) {
				pq.add(new Node(input));
			} else { // 0인 경우
				if (pq.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					sb.append(pq.poll().v).append("\n");
				}
			}
		}

		System.out.println(sb);			
	}
}
