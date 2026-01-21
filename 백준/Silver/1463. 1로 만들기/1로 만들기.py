# 1로 만들기

# 입력
x = int(input())

dp = [0] * (10**6 + 1)

# dp 테이블 채우기
for i in range(2, x + 1):
    # 1. 현재의 수에서 1을 빼는 경우
    dp[i] = dp[i - 1] + 1
    # 2. 현재의 수가 2로 나누어 떨어지는 경우
    if i % 2 == 0:
        dp[i] = min(dp[i], dp[i // 2] + 1)
    # 3. 현재의 수가 3으로 나누어 떨어지는 경우
    if i % 3 == 0:
        dp[i] = min(dp[i], dp[i // 3] + 1)

print(dp[x])