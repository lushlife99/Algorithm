def solution(n, s):
    answer = []
    
    if s // n == 0:
        return [-1]
        
    a = s // n
    b = s % n
        
    for i in range(n-b):
        answer.append(a)
        
    for i in range(b):
        answer.append(a+1)
            
    return answer