def solution(s):
    answer = 0
    stk = []
    
    for c in s:
        if stk and stk[-1] == c:
            stk.pop()
        else:
            stk.append(c)
    
    if len(stk) == 0:
        answer = 1

    return answer