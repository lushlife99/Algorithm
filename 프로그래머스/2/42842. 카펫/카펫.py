def solution(brown, yellow):
    answer = []
    extent = brown + yellow
    for width in range(extent - 1, 1, -1):
        if extent % width != 0:
            continue
        
        height = extent / width
        yellow_extent = (width - 2) * (height - 2)
        if yellow_extent == yellow and extent - yellow_extent == brown:
            answer = [width, height]
            break
            
    return answer