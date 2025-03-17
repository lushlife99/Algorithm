res = []

def dfs(airport, graph, visited, answer):
    
    if len(answer) == len(visited) + 1:
        res.append(list(answer))
        return
    
    if airport not in graph:
        return
    
    for next, ticket_idx in graph[airport]:
        
        if not visited[ticket_idx]:
            visited[ticket_idx] = True
            answer.append(next)
            dfs(next, graph, visited, answer)
            visited[ticket_idx] = False
            answer.pop()
            
    

def solution(tickets):
    answer = []
    graph = {}
    visited = [False for _ in range(len(tickets))]
    for i in range(len(tickets)):
        s, e = tickets[i]
        if s in graph:
            graph[s].append((e, i))
        else:
            graph[s] = [(e, i)]
        
    dfs("ICN", graph, visited, ["ICN"])
    res.sort()
    return res[0]