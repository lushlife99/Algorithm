import sys
from queue import PriorityQueue
input = sys.stdin.readline
n = int(input())
sum = 0
pq = PriorityQueue()
if n < 2:
    print(0)

else:
    for i in range(n):
        pq.put(int(input()))

    while pq.qsize() > 1:
        num1 = pq.get()
        num2 = pq.get()
        sum += num1 + num2
        pq.put(num1 + num2)

    print(sum)
