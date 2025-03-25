def solution(s):
    answer = True
    stk = []
    for i in range(len(s)):
        c = s[i]
        if c == '(':
            stk.append(c)
        elif len(stk) > 0 and stk[-1] == '(':
            stk.pop()
        else: return False
    
    if len(stk) > 0:
        return False
    return True