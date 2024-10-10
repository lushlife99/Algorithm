import sys
INF = 1e9
input = sys.stdin.readline

def bellman_ford(start, edge_list, total_node):

    distance = [INF for _ in range(total_node + 1)]
    distance[start] = 0

    for i in range(total_node):
        for current_node in range(1, total_node + 1):
            for next_node, cost in edge_list[current_node]:
                if distance[next_node] > distance[current_node] + cost:
                    distance[next_node] = distance[current_node] + cost
                    if i == total_node - 1:
                        return True
    return False

TC = int(input())

for _ in range(TC):
    N, M, W = map(int, input().split())
    edges = [[] for _ in range(N+1)]
    sig = True

    for _ in range(M):
        s, e, w = map(int, input().split())
        edges[s].append((e, w))
        edges[e].append((s, w))

    for _ in range(W):
        s, e, w = map(int, input().split())
        edges[s].append((e, -w))

    if bellman_ford(1, edges, N):
        print("YES")

    else:
        print("NO")
