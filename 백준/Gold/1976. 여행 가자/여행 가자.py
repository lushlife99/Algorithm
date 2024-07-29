import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

n = int(input()) # count of City
m = int(input()) # count of Plan

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
    a = find(a)
    b = find(b)

    if a != b:
        A[b] = a

for i in range(1, n+1):
    C = list(map(int, input().strip().split()))
    for j in range(len(C)):
        if C[j] == 1:
            union(min(i, j+1), max(j+1, i))

B = list(map(int, input().strip().split()))

ans = "YES"

for i in range(len(B) - 1):
    if find(B[i]) != find(B[i+1]):
        ans = "NO"
        break

print(ans)