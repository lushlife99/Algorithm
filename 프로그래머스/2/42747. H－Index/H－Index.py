# 논문의 개수를 구함
# 인용 반복문
# 

from collections import Counter

def solution(citations):
    answer = 0
    counter = Counter(citations)
    for i in range(10000, 0, -1):
        counter[i-1] += counter[i]
    
    for h in range(10000, 0, -1):
        if counter[h] >= h:
            answer = h
            break
            
    return answer