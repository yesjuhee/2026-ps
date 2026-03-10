import java.io.*;
import java.util.*;

public class Main {

	private int n;
	private int[] target;
	private Deque<Integer> stack = new ArrayDeque<>();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// 입력
		n = Integer.parseInt(br.readLine());
		target = new int[n];
		for (int i = 0; i < n; i++) {
			target[i] = Integer.parseInt(br.readLine());
		}

		// 계산
		int num = 1; // 오름차순 숫자
		for (int i = 0; i < n; i++) {
			int a = target[i]; // 수열의 i번째 수

			// 나 보다 작으면 계속 push
			while(stack.isEmpty() || stack.getLast() < a) { // 추가로 넣어야 하는 상황
				stack.addLast(num);
				num++;
				sb.append("+").append("\n");
			}

			if (stack.getLast() != a) {
				System.out.println("NO");
				return;
			}

			// 마지막 들어간게 a와 같음 -> pop 가능
			stack.removeLast();
			sb.append("-").append("\n");
		}

		// 출력
		System.out.print(sb);
	}
}
