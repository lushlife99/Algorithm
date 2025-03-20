INF = int(1e9)

def floyd_warshall(graph, n):
    distances = [[graph[i][j] for j in range(n+1)] for i in range(n+1)]
    
    for k in range(1, n+1):  # 경유지
        for i in range(1, n+1):  # 출발지
            for j in range(1, n+1):  # 도착지
                distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
        
    return distances

def solution(n, s, a, b, fares):
    answer = INF
    graph = [[INF for _ in range(n+1)] for _ in range(n+1)]
    for n1,n2, fare in fares:
        graph[n1][n2] = fare
        graph[n2][n1] = fare
    
    for i in range(1, n+1):
        graph[i][i] = 0
    
    distances = floyd_warshall(graph, n)
    
    answer = distances[s][a] + distances[s][b]
    
    for i in range(1, n+1):
        answer = min(answer, distances[s][i] + distances[i][a] + distances[i][b])
    
    return answer