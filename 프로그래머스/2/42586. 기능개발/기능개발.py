def solution(progresses, speeds):
    answer = []
    deploy_idx = 0
    end = []
    for i in range(len(progresses)):
        end_time = (100 - progresses[i]) // speeds[i]
        if (100 - progresses[i]) % speeds[i] != 0:
            end_time += 1
        end.append(end_time)
        
    time = 0
    while deploy_idx < len(progresses):
        cnt = 1
        time = end[deploy_idx]
        deploy_idx += 1
        while deploy_idx < len(progresses):
            if end[deploy_idx] <= time:
                cnt += 1
                deploy_idx += 1
            else:
                break
                
        answer.append(cnt)
        
    return answer