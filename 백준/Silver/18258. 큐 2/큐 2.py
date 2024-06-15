import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


queue = deque()
N = int(input())
for i in range(N):
    command = input().split()
    if command[0] == 'push':
        queue.append(int(command[1]))
    if command[0] == 'front':
        if len(queue) == 0:
            print(-1)
        else:
            print(queue[0])
    if command[0] == 'back':
        if len(queue) == 0:
            print(-1)
        else:
            print(queue[-1])
    if command[0] == 'size':
        print(len(queue))
    if command[0] == 'empty':
        if queue:
            print(0)
        else:
            print(1)
    if command[0] == 'pop':
        if queue:
            print(queue.popleft())
        else:
            print(-1)