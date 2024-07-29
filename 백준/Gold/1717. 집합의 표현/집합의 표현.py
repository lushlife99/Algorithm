import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n, m = map(int, input().strip().split())
A = [i for i in range(n+1)]

def find(a):
    global A
    if a == A[a]:
        return a
    else:
        A[a] = find(A[a])
        return A[a]

def union(a, b):
    global A
    a = find(A[a])
    b = find(A[b])

    if a != b:
        A[b] = a

for i in range(m):
    k, a, b = map(int, input().strip().split())

    if k == 0 :
        union(min(a,b), max(b,a))
    elif k == 1:
        if find(a) == find(b):
            print("YES")
        else: print("NO")