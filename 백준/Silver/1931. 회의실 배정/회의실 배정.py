import sys
from queue import PriorityQueue

input = sys.stdin.readline
pq = PriorityQueue()
n = int(input())
currentTime = 0
count = 0
for _ in range(n):
    start, end = map(int, input().strip().split())
    pq.put((end, start))

while pq.qsize() > 0:
    end, start = pq.get()
    if start >= currentTime:
        count += 1
        currentTime = end

print(count)