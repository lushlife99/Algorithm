import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]

for _ in range(M):
    n1, n2, w = map(int, input().split())
    graph[n1].append((n2, w))
    graph[n2].append((n1, w))

start, end = map(int, input().split())

def bfs(weight):
    visited = [False] * (N+1)
    queue = deque([start])
    visited[start] = True
    
    while queue:
        cur_node = queue.popleft()
        if cur_node == end:
            return True
        
        for next_node, next_weight in graph[cur_node]:
            if not visited[next_node] and next_weight >= weight:
                visited[next_node] = True
                queue.append(next_node)
    
    return False

# 이분 탐색
low, high = 1, 1000000000
answer = low

while low <= high:
    mid = (low + high) // 2
    if bfs(mid):  # 현재 중량으로 이동 가능하면
        answer = mid
        low = mid + 1  # 더 높은 중량을 시도
    else:
        high = mid - 1  # 중량을 낮춤

print(answer)
