from collections import Counter

def solution(clothes):
    
    counter = Counter([category for _, category in clothes])
    combinations = 1
    for count in counter.values():
        combinations *= (count + 1)
    
    return combinations - 1