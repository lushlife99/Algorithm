import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n = int(input())
visited = [False] * n
list = [[] for _ in range(n)]
D = [0] * n
lcm = 1
def gcd(a, b):

    if b ==0:
        return a
    else:
        return gcd(b, a%b)

def DFS(v):

    visited[v] = True
    for i in list[v]:
        if not visited[i[0]]:
            D[i[0]] = D[v] * i[2] // i[1]
            DFS(i[0])

for i in range(n-1):
    a, b, p, q = map(int, input().strip().split())
    list[a].append((b, p, q))
    list[b].append((a, q, p))
    lcm *= (p * q // gcd(p,q))
D[0] = lcm
DFS(0)
mgcd = D[0]

for i in range(1,n):
    mgcd = gcd(mgcd, D[i])

for i in range(n):
    print(int(D[i] // mgcd), end=' ')