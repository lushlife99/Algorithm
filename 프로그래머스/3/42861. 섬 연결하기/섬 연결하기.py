import heapq

def find(x, parent):
    if parent[x] != x:
        return find(parent[x], parent)
    return x

def union(a, b, parent):
    a = find(a, parent)
    b = find(b, parent)
    
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
    

def solution(n, costs):
    
    answer = 0
    parent = [i for i in range(n)]
    print(parent)
    costs = sorted(costs, key=lambda x: x[2])
    print(costs)
    
    for a, b, cost in costs:
        if find(a, parent) != find(b, parent):
            union(a, b, parent)
            answer += cost
        
    return answer