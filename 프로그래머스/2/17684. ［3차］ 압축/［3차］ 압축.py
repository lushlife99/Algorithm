# map에 저장
# 첫글자를 색인으로 

def solution(msg):
    answer = []
    index = {}
    last_index = 1
    
    for i in range(ord('A'), ord('Z') + 1):
        index[chr(i)] = last_index
        last_index += 1
    
    s = 0
    while s < len(msg):
        e = s + 1
        while e <= len(msg) and msg[s:e] in index:
            e += 1
        
        answer.append(index[msg[s:e-1]])

        if e <= len(msg):
            index[msg[s:e]] = last_index
            last_index += 1
        
        s = e - 1
                
    return answer