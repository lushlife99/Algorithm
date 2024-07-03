import sys
import math
input = sys.stdin.readline

n, m = map(int, input().split())
mylist = list(map(int, input().split()))
sum = 0
remainList = []
for value in mylist:
    sum += value
    sum %= m
    remainList.append(sum)

mydict = dict()

for value in remainList:
    if mydict.__contains__(value) : mydict[value] += 1
    else: mydict[value] = 1

answer = 0
if mydict.__contains__(0) : answer = mydict[0]

for key, value in mydict.items():
    answer += math.comb(value, 2)



print(answer)