import sys

input = sys.stdin.readline
INF = sys.maxsize

N, M = map(int, input().split())
distance = [[INF for _ in range(N)] for _ in range(N)]
res = 0


for _ in range(M):
    a, b = map(int, input().split())
    distance[a-1][b-1] = 1
    distance[b-1][a-1] = 2

for k in range(N):
    for i in range(N):
        for j in range(N):
            if distance[i][k] != INF and distance[i][k] == distance[k][j]:
                distance[i][j] = distance[i][k]

for i in range(N):
    sig = True
    for j in range(N):
        if i != j and distance[i][j] == INF:
            sig = False
            break

    if sig:
        res += 1

print(res)