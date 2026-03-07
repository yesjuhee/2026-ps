import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Character> editor = new LinkedList<>();
    int m;
    ListIterator<Character> iter;
    
	public static void main(String[] args) throws IOException {
        /*
        최대 600,000 글자 
        초기 문자 100,000 
        명령어 500,000

        삭제/추가
        ArrayList -> O(M * N)
        LinkedList -> O(M)

        abcd| (4)
        abcdx| (5)
        abcd|x (4)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            editor.add(str.charAt(i));
        }
        m = Integer.parseInt(br.readLine());
        iter = editor.listIterator(editor.size());
        
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            
            if (cmd == 'L') {
                if (iter.hasPrevious()) iter.previous();
            } else if (cmd == 'D') {
                if (iter.hasNext()) iter.next();
            } else if (cmd == 'B') {
                if (iter.hasPrevious()) {
                    iter.previous();
                    iter.remove();
                }
            } else {
                char c = st.nextToken().charAt(0);
                iter.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c: editor) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
