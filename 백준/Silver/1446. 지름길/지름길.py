import sys, heapq
INF = int(1e9)

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, 0))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue

        for i in graph[now]:
            if dist+i[1] < distance[i[0]]:
                distance[i[0]] = dist+i[1]
                heapq.heappush(q, (dist+i[1], i[0]))


N, D = map(int, input().split())
graph = [[] for _ in range(D+1)]
distance = [0] + [INF] * D

for i in range(D):
    graph[i].append([i+1, 1])

for _ in range(N):
    s, e, w = map(int, input().split())
    if e <= D and e - s > w:
        graph[s].append((e, w))

dijkstra(0)
print(distance[D])
