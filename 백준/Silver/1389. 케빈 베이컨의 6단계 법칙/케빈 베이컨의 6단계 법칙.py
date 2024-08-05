import sys
input = sys.stdin.readline
INF = sys.maxsize

n, m = map(int, input().split())
graph = [[INF for _ in range(n+1)] for _ in range(n+1)]

for i in range(1, n+1):
    graph[i][i] = 0

for i in range(m):
    a, b = map(int, input().split())
    graph[a][b] = 1
    graph[b][a] = 1

for k in range(1, n+1):
    for s in range(1, n+1):
        for e in range(1, n+1):
            graph[s][e] = min(graph[s][e], graph[s][k] + graph[k][e])

min_value = INF
min_idx = INF

for i in range(1, n+1):
    sum = 0
    for j in range(1, n+1):
        sum += graph[i][j]

    if min_value > sum:
        min_value = sum
        min_idx = i

print(min_idx)