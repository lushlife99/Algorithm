import sys

def input():
    return sys.stdin.readline().rstrip()

N = input()
score_list = input().split()
sum = 0
max_score = 0
for score in score_list:
    sum += int(score)
    if int(score) > int(max_score):
        max_score = score

avg = sum / int(N)

print(avg/int(max_score)*100)

