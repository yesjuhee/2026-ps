import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc, n;
    Map<String, Integer> clothes;
    
	public static void main(String[] args) throws IOException {
        /*
        의상 종류에 대해 한 번씩 
        headgear 2종류
        eyewear 1종류
        face 3종류 

        백트래킹으로 모든 경우의 수 카운트
        모든 타입은 0번또는 1번 쓰인다 
        한 타입을 쓰는 경우 -> 리스트에서 하나씩 
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());            
            clothes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String value = st.nextToken();
                String key = st.nextToken();
                clothes.put(key, clothes.getOrDefault(key, 0) + 1);
            }
            int result = 1;
            for (int cnt: clothes.values()) {
                result *= (cnt + 1);
            }
            System.out.println(result - 1);
        }
    }
}
