import heapq

INF = 1e9

M, N = map(int, input().split())
graph = [list(map(int, input())) for _ in range(N)]
distance = [[INF] * M for _ in range(N)]
dx = [1, 0, 0, -1]
dy = [0, 1, -1, 0]

def dijkstra():

    q = []
    heapq.heappush(q, (0, 0))
    distance[0][0] = 0

    while q:
        now_x, now_y = heapq.heappop(q)

        for i in range(4):
            nx = now_x + dx[i]
            ny = now_y + dy[i]

            if 0 <= nx < N and 0 <= ny < M:

                if graph[nx][ny] == 0 and distance[nx][ny] > distance[now_x][now_y]:
                    distance[nx][ny] = distance[now_x][now_y]
                    heapq.heappush(q, (nx, ny))

                elif graph[nx][ny] == 1 and distance[nx][ny] > distance[now_x][now_y] + 1:
                    distance[nx][ny] = distance[now_x][now_y] + 1
                    heapq.heappush(q, (nx, ny))

dijkstra()
print(distance[N-1][M-1])