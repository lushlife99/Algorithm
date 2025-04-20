# 스택 사용하면 간단하게 풀릴듯?

def solution(number, k):
    stk = []
    total_length = len(number) - k
    target = 0
    
    while target < len(number):
        while stk and stk[-1] < number[target] and total_length - len(stk) <= len(number) - target - 1:
            stk.pop()
        if len(stk) < total_length:
            stk.append(number[target])
        target += 1
        
    return "".join(stk)