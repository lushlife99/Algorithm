def solution(m, n, puddles):
    MOD = 1_000_000_007
    dp = [[0 for _ in range(m+1)] for _ in range(n+1)]
    puddles = set((y, x) for x, y in puddles)

    dp[1][1] = 1

    for i in range(1, n+1):
        for j in range(1, m+1):
            if (i, j) in puddles:
                dp[i][j] = 0
            else:
                if i > 1:
                    dp[i][j] += dp[i-1][j]
                if j > 1:
                    dp[i][j] += dp[i][j-1]
                dp[i][j] %= MOD

    return dp[n][m]