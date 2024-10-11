import sys

input = sys.stdin.readline

M = int(input())
S = 0
for i in range(M):
    command = list(map(str, input().split()))
    if command[0] == "add":
        S |= (1 << int(command[1]))
    elif command[0] == "check":
        print(1 if S & (1 << int(command[1])) != 0 else 0)
    elif command[0] == "remove":
        S &= ~(1 << int(command[1]))
    elif command[0] == "toggle":
        S ^= (1 << int(command[1]))
    elif command[0] == "all":
        S = (1 << 21) - 1
    elif command[0] == "empty":
        S = 0
