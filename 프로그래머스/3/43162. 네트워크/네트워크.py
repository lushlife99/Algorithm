import sys
sys.setrecursionlimit(100000)

def dfs(node, visited, computers):
    visited[node] = True
    for i in range(len(computers[node])):
        if computers[node][i] == 1 and not visited[i]:
            dfs(i, visited, computers)

def solution(n, computers):
    answer = 0
    visited = [False] * n
    
    for i in range(n):
        if not visited[i]:
            dfs(i, visited, computers)
            answer += 1
    
    return answer
