def solution(routes):
    answer = 1
    arr = []
    for s, e in routes:
        arr.append((e, s))
    
    arr.sort()
    end = arr[0][0]
    
    for e, s in arr:
        if s <= end <= e:
            continue
        
        end = e
        answer += 1
        
    return answer

