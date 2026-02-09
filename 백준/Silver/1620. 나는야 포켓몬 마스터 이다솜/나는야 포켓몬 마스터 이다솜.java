import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    String[] names;
    Map<String, Integer> numbers;

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        names = new String[n + 1];
        numbers = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            names[i] = name;
            numbers.put(name, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String input = br.readLine();
            Character c = input.charAt(0);
            if (Character.isDigit(c)) {
                int num = Integer.parseInt(input);
                sb.append(names[num]).append("\n");
            } else {
                sb.append(numbers.get(input)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
