import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("push")) {
                int v = Integer.parseInt(st.nextToken());
                queue.addLast(v);
            } else if (cmd.equals("front")) {
                if (queue.isEmpty()) sb.append("-1").append("\n");
                else sb.append(queue.getFirst()).append("\n");
            } else if (cmd.equals("back")) {
                if (queue.isEmpty()) sb.append("-1").append("\n");
                else sb.append(queue.getLast()).append("\n");
            } else if (cmd.equals("size")) {
                sb.append(queue.size()).append("\n");
            } else if (cmd.equals("empty")) {
                if (queue.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else { // pop
                if (queue.isEmpty()) sb.append("-1").append("\n");
                else sb.append(queue.removeFirst()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
