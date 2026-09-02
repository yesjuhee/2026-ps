import java.util.*;

/*
1. 자르기 O(n)
2. 정렬 O(nlogn)
3. 고르기 O(1)

100 x 50
*/

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int n = commands.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1];
            int k = commands[i][2] - 1;
            
            // 자르기
            int[] cuttingArr = new int[end - start];
            for (int j = 0; j < cuttingArr.length; j++) {
                cuttingArr[j] = array[start + j];
            }
            
            // 정렬
            Arrays.sort(cuttingArr);
            
            result[i] = cuttingArr[k];
        }
        
        return result;
    }
}