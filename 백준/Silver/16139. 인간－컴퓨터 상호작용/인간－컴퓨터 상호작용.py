import sys

s = str(input())
a_ascii = ord('a')
sum_list = [[0 for _ in range(len(s))] for _ in range(26)]
Q = int(input())

def solve():
    for i in range(len(s)):
        char = s[i]
        for j in range(26):
            sum_list[j][i] = sum_list[j][i-1]
        sum_list[ord(char) - a_ascii][i] += 1


solve()
for _ in range(Q):
    c, s, e = map(str, sys.stdin.readline().split())
    if int(s) == 0:
        print(sum_list[ord(c) - a_ascii][int(e)])
    else :print(sum_list[ord(c) - a_ascii][int(e)] - sum_list[ord(c) - a_ascii][int(s) - 1])



