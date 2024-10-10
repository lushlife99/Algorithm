import sys

input = sys.stdin.readline
INF = sys.maxsize

n, m, r = map(int, input().split())
item = list(map(int, input().split()))
edge = [list(map(int, input().split())) for _ in range(r)]
distance = [[INF for _ in range(n+1)] for _ in range(n+1)]
res = []

for i in range(1, n+1):
    distance[i][i] = 0

for i in range(len(edge)):
    n1, n2, cost = edge[i]
    distance[n1][n2] = cost
    distance[n2][n1] = cost

for k in range(1, n+1):
    for i in range(1, n+1):
        for j in range(1, n+1):
            if distance[i][j] > distance[i][k] + distance[k][j]:
                distance[i][j] = distance[i][k] + distance[k][j]

for i in range(1, n+1):
    ans = 0
    for j in range(1, n+1):
        if distance[i][j] <= m:
            ans += item[j-1]

    res.append(ans)

print(max(res))