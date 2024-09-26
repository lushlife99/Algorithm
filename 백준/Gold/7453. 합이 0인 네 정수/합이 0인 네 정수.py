import sys
input = sys.stdin.readline

n = int(input())
arr = [[0 for _ in range(n)] for _ in range(4)]
ab_sum_dict = {}
cnt = 0

for i in range(n):
    a, b, c, d = map(int, input().split())
    arr[0][i] = a
    arr[1][i] = b
    arr[2][i] = c
    arr[3][i] = d

for i in range(n):
    for j in range(n):
        ab_sum = arr[0][i] + arr[1][j]
        ab_sum_dict[ab_sum] = ab_sum_dict.get(ab_sum, 0) + 1

for i in range(n):
    for j in range(n):
        cd_sum = arr[2][i] + arr[3][j]
        cnt += ab_sum_dict.get(-cd_sum, 0)

print(cnt)
