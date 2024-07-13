import sys
import math
from queue import PriorityQueue

input = sys.stdin.readline

a, b = map(int, input().strip().split())
c = a
d = b
while True:
    mod = max(c, d) % min(c, d)
    if mod == 0:
        break

    d = min(c, d)
    c = mod

gcd = min(c, d)
for i in range(gcd):
    print(1, end='')
