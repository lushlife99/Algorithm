def solution(s):
    answer = 0
    size = len(s)
    for i in range(size):
        stk = []
        sig = True
        for j in range(size):
            if s[(i+j) % size] in ["[", "(", "{"]:
                stk.append(s[(i+j) % size])
            else:
                if len(stk) != 0:
                    spell = s[(i+j) % size]
                    if spell == "]" and stk[-1] == "[":
                        stk.pop()
                    elif spell == ")" and stk[-1] == "(":
                        stk.pop()
                    elif spell == "}" and stk[-1] == "{":
                        stk.pop()
                else:
                    sig = False
                    break
        if sig and len(stk) == 0:
            answer += 1
                        
    return answer