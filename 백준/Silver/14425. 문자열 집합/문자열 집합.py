import sys

list = []
N, M = map(int, sys.stdin.readline().strip().split())
cnt = 0
for i in range(N):
    list.append(sys.stdin.readline())

for i in range(M):
    string = sys.stdin.readline()
    if list.__contains__(string):
        cnt += 1


print(cnt)