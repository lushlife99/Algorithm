M, N = map(int, input().split())
K = int(input())
planet = [[[0,0,0] for i in range(N+1)] for j in range(M+1)]

for i in range(M):
    s = str(input())
    for j in range(N):
        for l in range(3):
            planet[i+1][j+1][l] = planet[i+1][j][l] +planet[i][j+1][l]- planet[i][j][l]
        if s[j]=='J':
            planet[i+1][j+1][0] += 1
        elif s[j]=='O':
            planet[i+1][j+1][1] +=1
        elif s[j]=='I':
            planet[i+1][j+1][2] += 1

for _ in range(K):
    a, b, c, d = map(int, input().split())
    answer = [0,0,0]
    for i in range(3):
        answer[i] = planet[c][d][i]-planet[a-1][d][i] - planet[c][b-1][i] + planet[a-1][b-1][i]
    print(answer[0], answer[1], answer[2])