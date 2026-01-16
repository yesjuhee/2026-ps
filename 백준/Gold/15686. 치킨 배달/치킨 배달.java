import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    List<Pair> houses = new ArrayList<>();
    List<Pair> chickens = new ArrayList<>();

    boolean[] isused; // 치킨이 선택받았는지 여부
    int min = 2500 * 20;
    
	public static void main(String[] args) throws IOException {
        /*
        치킨 거리: 집과 가장 가까운 치킨집 사이의 거리
        도시의 치킨 거리: 모든 집의 치킨 거리 합
        거리 : 행 차 + 열 차

        M개 남겼을 때 치킨 거리 최소값

        모든 경우의 수 다 돌려보기
        - 치킨 집 : M개 ~ 13개
        - 폐업할 치킨집: M-13 -> 최대 12개
        경우의 수 : 13C6 정도?

        각각에 대해 치킨 거리 구하기
        (집 수) * (치킨집 수) -> 2n * m -> 500 정도

        c개 중에 m개를 고르는 경우의 수 -> 백트래킹
        순열로 해도 결과는 똑같긴 함
        근데 경우의 수 차이 많이 날 듯 -> 일단 해보자
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                char v = st.nextToken().charAt(0);
                if (v == '1') {
                    houses.add(new Pair(i, j));
                } else if (v == '2') {
                    chickens.add(new Pair(i, j));
                }
            }
        }
        isused = new boolean[chickens.size()];

        // 치킨 집 중 m개 고르는 백트래킹 실행
        func(0, -1);

        // 최소값 출력
        System.out.println(min);
	}

    int calculateChickenDistance() {
        // 도시의 치킨 거리 구하기
        int cnt = 0;
        for (int h = 0; h < houses.size(); h++) {
            Pair house = houses.get(h);
            int dist = 2500 * 20; // 이 집의 치킨 거리
            for (int c = 0; c < chickens.size(); c++) {
                if (!isused[c]) continue; // 폐업 지점
                Pair chicken = chickens.get(c);
                int tmpDist = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                dist = Math.min(tmpDist, dist);                
            }
            cnt += dist;
        }
        return cnt;
    }

    void func(int k, int last) { // k 선택한 치킨집 개수 , last: 제일 마지막 값
        if (k == m) {
            // isused 에서 선택된 치킨과 치킨 거리 구하기
            int tmp = calculateChickenDistance();
            min = Math.min(min, tmp);
            return;
        }
        for (int i = last + 1; i < chickens.size(); i++) {
            if (isused[i]) continue;
            isused[i] = true;
            func(k + 1, i);
            isused[i] = false;
        }
    }

    class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
