import sys

N, S, E, M = map(int, input().split())
edges = [list(map(int, input().split())) for _ in range(M)]
moneys = list(map(int, input().split()))
INF = sys.maxsize
distance = [-INF] * N

def bellman_ford(start):

    distance[start] = moneys[start]

    for i in range(N*2 + M):
        for j in range(M):
            start, end, cost = edges[j]

            if distance[start] == -INF:
                continue
            
            if distance[end] < distance[start] - cost + moneys[end]:
                if i < N-1:
                    distance[end] = distance[start] - cost + moneys[end]
                else:
                    distance[end] = INF

    if distance[E] == INF:
        return "Gee"

    elif distance[E] == -INF:
        return "gg"

    else: return distance[E]

print(bellman_ford(S))