import heapq

def solution(operations):
    answer = []
    minh = []
    maxh = []
    
    for op in operations:
        if op[0] == 'I':
            heapq.heappush(minh, int(op.split()[-1]))
            heapq.heappush(maxh, -int(op.split()[-1]))
            continue
            
        if len(minh) == 0:
            continue
        
        if int(op.split()[-1]) == 1:
            minh.remove(-heapq.heappop(maxh))
            continue
            
        maxh.remove(-heapq.heappop(minh))
    
    if len(minh) == 0:
        return [0,0]
    
    answer.append(max(minh))
    answer.append(min(minh))
    return answer