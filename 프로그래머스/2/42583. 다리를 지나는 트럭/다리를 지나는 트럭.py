from collections import deque

def solution(bridge_length, weight, truck_weights):
    q = deque()
    total_weight = 0
    time = 0
    for w in truck_weights:
        time+=1
        if total_weight + w > weight:
            
            while q and total_weight + w > weight:
                exit_time, exit_weight = q.popleft()
                time = max(time, exit_time)
                total_weight -= exit_weight
            
        if len(q) == bridge_length:
            exit_time, exit_weight = q.popleft()
            time = max(time, exit_time)
            total_weight -= exit_weight
        
        q.append((time+bridge_length, w))
        total_weight += w
    
    return q[-1][0]