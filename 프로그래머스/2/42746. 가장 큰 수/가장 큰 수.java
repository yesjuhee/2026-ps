import java.util.*;

/*
정렬 기준 -> a, b 가 있을 떄 ab ba 비교해서 정렬시키기 
*/

class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(arr, (s1, s2) -> {
            String case1 = new String(s1 + s2);
            String case2 = new String(s2 + s1);
            return case2.compareTo(case1);
        });
        
        // 전체 연결하기
        if (arr[0].equals("0")) return "0";

        String answer = "";
        for (int i = 0; i < numbers.length; i++) {
            answer += arr[i];            
        }
        
        return answer;        
        
        
        
        
//         String[][] arr = new String[numbers.length][2]; // {orginal, padding};
        
//         for (int i = 0; i < numbers.length; i++) {
//             String original = Integer.toString(numbers[i]);
//             arr[i][0] = original;
//             String padding = new String(original);
//             while(padding.length() < 4) {
//                 padding += padding.substring(0, 1);
//             }
//             arr[i][1] = padding;
//         }
//         // System.out.println(Arrays.deepToString(arr));
        
//         Arrays.sort(arr, (a, b) -> {
//             // padding 내림차순 정렬, string
//             String s1 = a[1];
//             String s2 = b[1];
//             return s2.compareTo(s1);
//         });
        
//         if (arr[0][0].equals("0")) return "0";

//         String answer = "";
//         for (int i = 0; i < numbers.length; i++) {
//             answer += arr[i][0];            
//         }
        
//         return answer;
    }
}