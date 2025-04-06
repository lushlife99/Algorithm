def solution(triangle):
    answer = 0
    depth = 0
    queue = []
    queue.append((0, 1, triangle[0][0]))
    result = []
    
    for i in range(len(triangle)):
        arr = []
        for j in range(len(triangle[i])):
            arr.append(0)
        result.append(arr)
    
    result[0][0] = triangle[0][0]
    while depth < len(triangle) - 1:
        for i in range(len(triangle[depth])):
            if result[depth+1][i] < result[depth][i] + triangle[depth+1][i]:
                result[depth+1][i] = result[depth][i] + triangle[depth+1][i]
            if result[depth+1][i+1] < result[depth][i] + triangle[depth+1][i+1]:
                result[depth+1][i+1] = result[depth][i] + triangle[depth+1][i+1]
        depth+=1
    
    return max(result[depth])