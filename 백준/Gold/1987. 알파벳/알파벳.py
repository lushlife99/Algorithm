R, C = map(int, input().split())
arr = [['a' for _ in range(C)] for _ in range(R)]
max_count = 0
visited = [False] * 26

def dfs(posX, posY, count):

    global max_count, visited

    if max_count < count:
        max_count = count

    current_alphabet = arr[posX][posY]
    if posX + 1 < R and not visited[ord(arr[posX + 1][posY]) - ord('A')]:
        visited[ord(arr[posX + 1][posY]) - ord('A')] = True
        dfs(posX + 1, posY, count + 1)
        visited[ord(arr[posX + 1][posY]) - ord('A')] = False

    if posX - 1 >= 0 and not visited[ord(arr[posX - 1][posY]) - ord('A')]:
        visited[ord(arr[posX - 1][posY]) - ord('A')] = True
        dfs(posX - 1, posY, count + 1)
        visited[ord(arr[posX - 1][posY]) - ord('A')] = False

    if posY + 1 < C and not visited[ord(arr[posX][posY + 1]) - ord('A')]:
        visited[ord(arr[posX][posY + 1]) - ord('A')] = True
        dfs(posX, posY + 1, count + 1)
        visited[ord(arr[posX][posY + 1]) - ord('A')] = False

    if posY - 1 >= 0 and not visited[ord(arr[posX][posY - 1]) - ord('A')]:
        visited[ord(arr[posX][posY - 1]) - ord('A')] = True
        dfs(posX, posY - 1, count + 1)
        visited[ord(arr[posX][posY - 1]) - ord('A')] = False

for i in range(R):
    inputs = input()
    for j in range(len(inputs)):
        arr[i][j] = inputs[j]

visited[ord(arr[0][0]) - ord('A')] = True
dfs(0,0,1)

print(max_count)