from sys import stdin
import heapq

N, C =  map(int, stdin.readline().split())
M = int(stdin.readline())
arr = []
capa = [C]*N
total = 0

for _ in range(M):
    s, e, w = map(int, stdin.readline().split())
    arr.append((e, -w, s))

heapq.heapify(arr)
for e, w, s in arr:
    _min = C
    for i in range(s, e):
        if _min > min(capa[i], -w) :
            _min = min(capa[i], -w)
    for i in range(s, e):
        capa[i] -= _min
    total += _min
print(total)