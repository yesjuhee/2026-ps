import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n;
	StringBuilder sb = new StringBuilder();
	String bar = "____";

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		n == 2

		// 앞 뒤로 출력
		어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.
		"재귀함수가 뭔가요?"
		...
		라고 답변하였지.

		// n번 반복 출력
		"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.
		마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.
		그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."
		____"재귀함수가 뭔가요?"
		...		
		____라고 답변하였지.		
		*/

		n = Integer.parseInt(br.readLine());

		sb
		.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n")
		.append("\"재귀함수가 뭔가요?\"").append("\n");

		func(n, "");
		
		sb.append("라고 답변하였지.").append("\n");
		
		System.out.println(sb);
	}

	void func(int k, String suffix) {
		if (k == 0) {
			sb
			.append(suffix).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"").append("\n");
			return;
		}

		sb
		.append(suffix).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append("\n")
		.append(suffix).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append("\n")
		.append(suffix).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append("\n")
		.append(suffix).append("____\"재귀함수가 뭔가요?\"").append("\n");
		func(--k, suffix + bar);
		sb
		.append(suffix).append("____라고 답변하였지.").append("\n");
	}
}