N, M = map(int, input().split())
interests = []
graph = [[] for _ in range(N + 1)]
edges = [[] for _ in range(N + 1)]
answer = [0] * (N + 1)

def dfs(flower_num):
    global N
    if flower_num == N+1:
        print("YES")
        for i in range(1, N + 1):
            print(answer[i], end=' ')
        exit()

    for frog in graph[flower_num]: # 연꽃에 앉는 개구리
        if frog not in answer: # 연꽃이 선택 됐는지 확인
            if check(flower_num, frog):
                answer[flower_num] = frog
                dfs(flower_num + 1)
                answer[flower_num] = 0


def check(flower_num, frog):
    for other_flower, interest in edges[flower_num]:  # 통나무 확인
        if answer[other_flower] != 0 and interests[answer[other_flower] - 1][interest - 1] != interests[frog - 1][interest - 1]:
            return False

    return True


for _ in range(N):
    interests.append(list(map(int, input().split())))

for i in range(1, N + 1):
    node1, node2 = map(int, input().split())
    if node1 == node2:
        graph[node1].append(i)

    else:
        graph[node1].append(i)
        graph[node2].append(i)

for i in range(M):
    a, b, c = map(int, input().split())
    edges[a].append((b,c))
    edges[b].append((a,c))

dfs(1)
print("NO")
