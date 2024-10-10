import sys

input = sys.stdin.readline
INF = sys.maxsize

N = int(input())

distance = [list(map(int, input().split())) for _ in range(N)]

for k in range(N):
    for i in range(N):
        for j in range(N):
            if distance[i][k] == 1 == distance[k][j]:
                distance[i][j] = 1

for i in range(N):
    for j in range(N):
        print(distance[i][j], end=' ')
    print()