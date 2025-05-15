import heapq

def solution(data, ext, val_ext, sort_by):
    
    answer = []
    
    for d in data:
        if (ext == "code"):
            if (d[0] < val_ext):
                answer.append(d)
        elif (ext == "date"):
            if (d[1] < val_ext):
                answer.append(d)
    
        elif (ext == "maximum"):
            if (d[2] < val_ext):
                answer.append(d)
        else: 
            if (d[3] < val_ext):
                answer.append(d)
            
    if (sort_by == "code"):
        answer.sort(key=lambda a: a[0])
    elif sort_by == "date":
        answer.sort(key=lambda a: a[1])
    elif sort_by == "maximum":
        answer.sort(key=lambda a: a[2])
    elif sort_by == "remain":
        answer.sort(key=lambda a: a[3])
    
    
    return answer