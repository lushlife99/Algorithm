import heapq
INF = 1e10

def dijkstra(start, graph):
    q = []
    distances = [INF for _ in range(len(graph))]
    distances[start] = 0
    heapq.heappush(q, start)
    
    while q:
        now = heapq.heappop(q)
        
        for next in graph[now]:
            if distances[next] > distances[now] + 1:
                distances[next] = distances[now] + 1
                heapq.heappush(q, next)
                
    return distances

def solution(n, roads, sources, destination):
    answer = []
    graph = [[] for _ in range(n+1)]
    
    for s,e in roads:
        graph[s].append(e)
        graph[e].append(s)
    
    distances = dijkstra(destination, graph)
    
    for source in sources:
        if distances[source] == INF:
            answer.append(-1)
            continue
            
        answer.append(distances[source])
    
    return answer