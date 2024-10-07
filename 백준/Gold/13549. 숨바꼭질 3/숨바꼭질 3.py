import heapq
INF = 1e9

N, K = map(int, input().split())
graph = [[] for _ in range(100001)]
distance = [INF] * 100001

for i in range(1, 100001):
    graph[i].append((i-1, 1))
    graph[i].append((i+1, 1))
    if i * 2 < 100001:
        graph[i].append((i*2, 0))

graph[0].append((1,1))
graph[100000].append((99999, 1))

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue

        for i in graph[now]:
            if i[0] < 100001 and dist+i[1] < distance[i[0]]:
                distance[i[0]] = dist+i[1]
                heapq.heappush(q, (distance[i[0]], i[0]))

dijkstra(N)
print(distance[K])