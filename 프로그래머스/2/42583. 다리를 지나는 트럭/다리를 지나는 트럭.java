import java.util.*;

/*
다리 -> 일정한 사이즈의 Queue<Integer>
트럭이 안올라간 자리는 0으로 표현하기
1초에 하나씩 지남 
다리 전체의 무게는 하나의 int weightSum 으로 관리
*/

class Solution {
    
    Queue<Integer> bridge = new ArrayDeque<>();
    int time = 0;
    int weightSum = 0;
    
    public int solution(int bridgeLength, int maxWeight, int[] truckWeights) {
        int truckIndex = 0; // 운행 중인 트럭 
        
        for (int i = 0; i < bridgeLength; i++) {
            bridge.offer(0);
        }
        
        while(truckIndex < truckWeights.length) {
            // 앞 트럭 제거
            weightSum -= bridge.poll();
            
            int nextTruckWeight = truckWeights[truckIndex];
            if (nextTruckWeight + weightSum > maxWeight) {
                // 트럭 추가 못함 
                bridge.offer(0);
            } else {
                // 트럭 추가 가능
                bridge.offer(nextTruckWeight);
                weightSum += nextTruckWeight;
                truckIndex++;
            }
            time++;
        }
        while(weightSum > 0) {
            weightSum -= bridge.poll();
            time++;
        }
        
        return time;
    }
}