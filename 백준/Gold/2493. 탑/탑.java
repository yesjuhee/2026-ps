import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        핵심 아이디어 
        -> 오른쪽 탑이 왼쪽 탑보다 높다면, 왼쪽 탑은 더이상 수신 불가능 -> 버려도 됨
        왼쪽에서 하나씩 탐색하면서 높은 것만 남기기..
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        Deque<Pair> stack = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            
            while(!stack.isEmpty() && stack.getLast().height < height) {
                stack.removeLast();
            }

            if (stack.isEmpty()) {
                sb.append("0 ");
            } else {
                sb.append(stack.getLast().number).append(" ");
            }
            stack.addLast(new Pair(height, i + 1));
        }

        System.out.println(sb);
    }

    public class Pair {
        int height, number;

        public Pair(int height, int number) {
            this.height = height;
            this.number = number;
        }
    }
}
