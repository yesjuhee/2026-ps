import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            if (deque.peekLast() != num) deque.addLast(num);
        }
        
        int[] answer = new int[deque.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = deque.removeFirst();
        }

        return answer;
    }
}