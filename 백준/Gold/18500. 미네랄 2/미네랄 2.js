const fs = require('fs'); const input = fs .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt') .toString() .trim() .replace(/\r/g, '') .split('\n');

const [R, C] = input[0].split(' ').map(Number);

const board = input.slice(1, R + 1).map((v) => v.split(''));
const N = Number(input[R + 1]);
const H = input[R + 2].split(' ').map(Number);

const dirs = [
  [0, 1],
  [0, -1],
  [1, 0],
  [-1, 0],
];

function left(h) {
  for (let i = 0; i < C; i++) {
    if (board[R - h][i] === 'x') {
      board[R - h][i] = '.';
      break;
    }
  }
}

function right(h) {
  for (let i = C - 1; i >= 0; i--) {
    if (board[R - h][i] === 'x') {
      board[R - h][i] = '.';
      break;
    }
  }
}

function bfs(y, x, visited) {
  visited[y][x] = true;
  const queue = [[y, x]];
  const cluster = [[y, x]];
  while (queue.length) {
    const [y, x] = queue.shift();

    for (const [dy, dx] of dirs) {
      const ny = dy + y;
      const nx = dx + x;
      if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
      if (board[ny][nx] === '.' || visited[ny][nx]) continue;

      visited[ny][nx] = true;
      queue.push([ny, nx]);
      cluster.push([ny, nx]);
    }
  }
  return cluster;
}

// 시간 복잡도 O(RC + RC) = O(RC)
function find() {
  const visited = Array.from({ length: R }, () => Array(C).fill(false));

  for (let y = 0; y < R; y++) {
    for (let x = 0; x < C; x++) {
      if (board[y][x] === 'x' && !visited[y][x]) {
        const cluster = bfs(y, x, visited);

        const found = cluster.every(([y, x]) => y !== R - 1);
        if (found) return cluster;
      }
    }
  }
  return null;
}

// O(R^3C^2) -> RC * R * RC(some)
function drop(cluster) {
  // 1. 클러스터 먼저 지우기
  for (const [y, x] of cluster) {
    board[y][x] = '.';
  }

  // 2. 클러스터의 최대 낙하 거리 계산
  let min = R;
  for (const [y, x] of cluster) {
    let count = 0;
    for (let ny = y + 1; ny < R; ny++) {
      if (board[ny][x] === 'x') break; // 더 이상 내려갈 수 없으면 멈춤
      count++;
    }
    min = Math.min(min, count); // 클러스터 중 가장 빨리 바닥에 닿는 거리
  }

  // 3. 클러스터 새 위치에 배치
  for (const [y, x] of cluster) {
    board[y + min][x] = 'x';
  }
}


// 시간 복잡도 O(NR^2C)
for (let i = 0; i < H.length; i++) {
  // 1. 막대기 던짐
  const h = H[i];
  if (i % 2 === 0) left(h);
  else right(h);

  // 2. 클러스터 찾기
  const cluster = find();

  //3. 떨어뜨리기
  if (cluster) {
    drop(cluster);
  }
}

board.forEach((v) => console.log(v.join('')));
