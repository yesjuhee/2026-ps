import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, bridgeLength, maxWeight;
    Deque<Integer> truckQ = new ArrayDeque<>();
    Deque<Integer> bridge = new ArrayDeque<>();
    int weightSum = 0;
    int time = 0;

	public static void main(String[] args) throws IOException {
        /*
        구: 모든 트럭이 다리를 건너는 최단시간 
    
        1. 트럭 전체 입력 -> Queue
        2. 다리 -> Deque 이용 (최대 100개) -> 빈 트럭 추가해서 항상 w개 유지
        3. 다리의 현재 합 -> int로 관리 

        1. truckQ 가 빌 때 까지
        2. bridge의 트럭이 W개보다 크면 -> 앞에서 제거, weightSum --
            트럭 추가했을 떄 sum을 넘지 않는지 확인
            넘으면 0 추가
            넘지 않으면 트럭 추가
        3. 시간은 계속 카운트

        시간복잡도? O(n * w) = O(100,000)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        init();
        
        while(!truckQ.isEmpty()) {
            time++;
            
            if (bridge.size() == bridgeLength) {
                // 앞에서 하나 제거
                int removed = bridge.removeFirst();
                weightSum -= removed;
            }
            // 뒤에 추가
            if (weightSum + truckQ.peek() <= maxWeight) {
                int newTruck = truckQ.poll();
                bridge.addLast(newTruck);
                weightSum += newTruck;
            } else {
                bridge.addLast(0);
            }
        }
        // 마지막 트럭이 막 다리로 올라옴 -> 다 건너려면 다리의 길이 만큼 필요
        time += bridgeLength;

        System.out.println(time);
    }

    void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        bridgeLength = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truckQ.addLast(Integer.parseInt(st.nextToken()));
        }
    }
}
