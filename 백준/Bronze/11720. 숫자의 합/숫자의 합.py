import sys
import heapq
from collections import defaultdict

def input():
    return sys.stdin.readline().rstrip()

N = input()
M = input()

total_sum = 0
for i in M:
    total_sum += int(i)

print(total_sum)