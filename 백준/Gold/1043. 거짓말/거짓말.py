import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x])
    return parent[x]

def union(parent, rank, x, y):
    rootX = find(parent, x)
    rootY = find(parent, y)

    if rootX != rootY:
        if rank[rootX] > rank[rootY]:
            parent[rootY] = rootX
        elif rank[rootX] < rank[rootY]:
            parent[rootX] = rootY
        else:
            parent[rootY] = rootX
            rank[rootX] += 1

n, m = map(int, input().strip().split())
truth = list(map(int, input().strip().split()))
if truth[0] == 0:
    truth = []
else:
    truth = truth[1:]

parent = [i for i in range(n + 1)]
rank = [0] * (n + 1)

parties = []
for _ in range(m):
    party = list(map(int, input().strip().split()))[1:]
    parties.append(party)
    for i in range(1, len(party)):
        union(parent, rank, party[0], party[i])

# Find all groups that contain at least one person who knows the truth
truth_groups = set(find(parent, person) for person in truth)

lie_count = 0
for party in parties:
    if all(find(parent, person) not in truth_groups for person in party):
        lie_count += 1

print(lie_count)
