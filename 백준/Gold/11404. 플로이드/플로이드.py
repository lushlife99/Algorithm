import sys
input = sys.stdin.readline
INF = sys.maxsize
n = int(input())
m = int(input())
graph = [[INF for _ in range(n+1)] for _ in range(n+1)]

for i in range(1, n+1):
    graph[i][i] = 0

for i in range(m):
    a, b, c = map(int, input().split())
    graph[a][b] = min(c, graph[a][b])


for k in range(1, n+1):
    for s in range(1, n+1):
        for e in range(1, n+1):
            graph[s][e] = min(graph[s][e], graph[s][k] + graph[k][e])

for i in range(1, n+1):
    for j in range(1, n+1):
        if graph[i][j] == INF:
            print(0, end=' ')
        else:
            print(graph[i][j], end=' ')

    print()