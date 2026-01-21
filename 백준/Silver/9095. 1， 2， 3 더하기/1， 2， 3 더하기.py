# 1, 2, 3 더하기

dp = [0] * 12
dp[1] = 1
dp[2] = 2
dp[3] = 4

for tc in range(int(input())):
    # 입력
    n = int(input())
    # 다이나믹 프로그래밍
    for i in range(4, n+1):
        dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
    # 출력
    print(dp[n])