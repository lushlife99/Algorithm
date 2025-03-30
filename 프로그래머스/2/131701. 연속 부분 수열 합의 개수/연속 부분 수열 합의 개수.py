def solution(elements):
    sum_set = set()
    sums = [[0 for _ in range(len(elements))] for _ in range(len(elements))]
    
    for i in range(len(elements)):
        sums[i][0] = elements[i]
        for j in range(1, len(elements)):
            sums[i][j] = sums[i][j-1] + elements[(i+j) % len(elements)]
    
    for i in range(len(elements)):
        for j in range(len(elements)):
            sum_set.add(sums[i][j])
            
    return len(sum_set)