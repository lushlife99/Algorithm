import sys

N, M = map(int, input().split())
arr = [[0 for _ in range(M+1)]]
sum_list = [[0 for _ in range(M+1)] for _ in range(N+1)]
res = -sys.maxsize

for i in range(N):
    arr.append([0] + list(map(int, input().split())))

for i in range(1, N+1):
    for j in range(1, M+1):
        sum_list[i][j] = sum_list[i-1][j] + sum_list[i][j-1] - sum_list[i-1][j-1] + arr[i][j]

for i in range(1, N+1):
    for j in range(1, M+1):
        for k in range(0, i):
            for l in range(0, j):
                s = sum_list[i][j] - sum_list[k][j] - sum_list[i][l] + sum_list[k][l]
                res = max(s, res)

print(res)