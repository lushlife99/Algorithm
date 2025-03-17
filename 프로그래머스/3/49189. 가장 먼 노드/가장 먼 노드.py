import heapq

def dijkstra(start, graph):
    q = []
    distances = [0] + [1e6 for _ in range(len(graph) - 1)]
    heapq.heappush(q, (0, start))
    distances[start] = 0
    
    while q:
        dist, now = heapq.heappop(q)
        
        for next in graph[now]:
            if dist + 1 < distances[next]:
                distances[next] = dist + 1
                heapq.heappush(q, (dist + 1, next))
    
    return distances
    

def solution(n, edge):
    answer = 0
    graph = [[] for _ in range(n+1)]
    for n1, n2 in edge:
        graph[n1].append(n2)
        graph[n2].append(n1)
        
    dists = dijkstra(1, graph)
    max_ = max(dists)
    
    for i in range(1, n+1):
        if dists[i] == max_:
            answer += 1
        
    return answer