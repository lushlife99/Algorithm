def is_learnable(a, parents, visited): 
    if a == parents[ord(a) - 65]:
        return True
    
    if not visited[ord(parents[ord(a) - 65]) - 65]:
        return False
    
    return is_learnable(parents[ord(a) - 65], parents, visited)

def solution(skill, skill_trees):
    answer = 0
    parents = [chr(i) for i in range(65, 91)]
    
    for i in range(1, len(skill)):
        parents[ord(skill[i]) - 65] = skill[i-1]
        
    for skill_tree in skill_trees:
        visited = [False for _ in range(26)]
        answer += 1
        for i in range(len(skill_tree)):
            if skill_tree[i] == parents[ord(skill_tree[i]) - 65]:
                visited[ord(skill_tree[i]) - 65] = True
            elif is_learnable(skill_tree[i], parents, visited):
                visited[ord(skill_tree[i]) - 65] = True
            else:
                answer -= 1
                break
    
    return answer