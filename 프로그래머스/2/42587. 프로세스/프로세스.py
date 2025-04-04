import heapq

def solution(priorities, location):
    
    answer = 0
    queue = []
    for i in range(len(priorities)):
        queue.append((priorities[i], i))
        
    priorities.sort(reverse=True)
    cnt = 0
    while cnt < len(priorities):
        print(queue)
        for _ in range(len(priorities)):
            if queue[0][0] == priorities[cnt]:
                priority, idx = queue.pop(0)
                if idx == location:
                    answer = cnt+1
                break
            else:
                priority, idx = queue.pop(0)
                queue.append((priority, idx))
                
        cnt+=1
    
    return answer