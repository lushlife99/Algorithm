# [Gold IV] 색종이 올려 놓기 - 2643 

[문제 링크](https://www.acmicpc.net/problem/2643) 

### 성능 요약

메모리: 14388 KB, 시간: 120 ms

### 분류

다이나믹 프로그래밍, 정렬

### 제출 일자

2025년 7월 26일 15:46:57

### 문제 설명

<p>크기가 모두 다른 직사각형 모양의 색종이가 여러 장 있다. 색종이를 하나씩 올려 놓아, 되도록 많은 장수의 색종이들을 쌓으려고 한다.</p>

<p>새로 한 장의 색종이를 올려 놓을 때는 지금까지 쌓아 놓은 색종이들 중 맨 위의 색종이 위에 올려놓아야 하며 아래의 두 조건을 모두 만족해야 한다.</p>

<ul>
	<li>조건 1 : 새로 올려 놓는 색종이는 맨 위의 색종이보다 크지 않아야 한다. 즉, 맨 위의 색종이 밖으로 나가지 않아야 한다.</li>
	<li>조건 2 : 새로 올려 놓는 색종이와 맨 위의 색종이의 변들은 서로 평행해야 한다.(색종이를 90˚돌려 놓을 수 있다.)</li>
</ul>

<p>예를 들어, 아래의 그림 중에서 위의 두 조건을 모두 만족하는 경우는 (나)뿐이다.</p>

<table class="table table-bordered td-center">
	<tbody>
		<tr>
			<td><img alt="" src="https://upload.acmicpc.net/830e691c-1989-4613-8dc9-0257d20214fc/-/crop/216x156/0,0/-/preview/" style="width: 108px; height: 78px;"></td>
			<td><img alt="" src="https://upload.acmicpc.net/830e691c-1989-4613-8dc9-0257d20214fc/-/crop/188x156/246,0/-/preview/" style="width: 94px; height: 78px;"></td>
			<td><img alt="" src="https://upload.acmicpc.net/aded1664-9f0a-4026-bd52-37b978453881/-/preview/" style="width: 90px; height: 68px;"></td>
			<td><img alt="" src="https://upload.acmicpc.net/f18e7a59-08a6-4156-9690-e9ff061a9d1f/-/preview/" style="width: 94px; height: 68px;"></td>
		</tr>
		<tr>
			<td>(가)</td>
			<td>(나)</td>
			<td>(다)</td>
			<td>(라)</td>
		</tr>
	</tbody>
</table>

<p>색종이는 두 변의 길이로 표현된다. (3, 5)는 두 변의 길이가 각각 3과 5인 색종이를 나타낸다. 예를 들어, 다음과 같이 7장의 색종이가 주어졌다고 하자 : (1, 2), (8, 7), (20, 10), (20, 20), (15, 12), (12, 14), (11, 12) 위의 조건을 만족하면서 가장 많이 쌓을 수 있는 색종이들의 순서는 (20, 20), (15, 12), (12, 14), (11, 12), (8, 7), (1, 2)이다.</p>

<p>입력으로 색종이들이 주어졌을 때, 위의 조건 1과 조건 2를 만족하면서 쌓을 수 있는 최대 색종이 장수를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫 번째 줄에는 색종이의 장수가 주어진다. 다음 줄부터 각 줄에 색종이의 두 변의 길이가 주어진다. 두 변의 길이는 한 칸 띄어 주어진다. 색종이의 최대 장수는 100이고, 각 변의 길이는 1000보다 작은 자연수이다.</p>

### 출력 

 <p>쌓을 수 있는 최대 색종이 장수를 출력한다.</p>

