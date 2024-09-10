R, C, Q = map(int, input().split())
sum_list = [[0 for _ in range(C+1)] for _ in range(R+1)] # sum_list[i][j] = 0,0부터 i,j까지의 픽셀 합
pixels = []

for _ in range(R):
    pixels.append(list(map(int, input().split())))

for i in range(1, R+1):
    for j in range(1, C+1):
        sum_list[i][j] = pixels[i-1][j-1] + sum_list[i-1][j] + sum_list[i][j-1] - sum_list[i-1][j-1]


for _ in range(Q):
    r1, c1, r2, c2 = map(int, input().split())
    print((sum_list[r2][c2]
           - sum_list[r2][c1-1]
           - sum_list[r1-1][c2]
           + sum_list[r1-1][c1-1])
          // ((r2 - r1 + 1) * (c2 - c1 + 1)))
