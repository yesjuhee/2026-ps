import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
        new Main().solution();
	}

    public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        Set<String> names = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());    
            String name = st.nextToken();
            String log = st.nextToken();
            if (log.equals("enter")) {
                names.add(name);
            } else {
                names.remove(name);
            }
        }

        StringBuilder sb = new StringBuilder();
        List<String> output = new ArrayList<>();
        for (String name: names) {
            output.add(name);
        }
        Collections.sort(output);
        for (int i = output.size() - 1; i >= 0; i--) {
            sb.append(output.get(i)).append("\n");
        }
        System.out.println(sb);
    }
}
