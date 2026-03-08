import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t;
    
	public static void main(String[] args) throws IOException {
        /*
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            String input = br.readLine();
            List<Character> note = new LinkedList<>();
            ListIterator<Character> iter = note.listIterator();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '<') {
                    if (iter.hasPrevious()) iter.previous();
                } else if (c == '>') {
                    if (iter.hasNext()) iter.next();
                } else if (c == '-') {
                    if (iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                } else {
                    iter.add(c);
                }
            }
            for (char c: note) {
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
