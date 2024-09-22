from collections import defaultdict, deque

# BFS를 통해 연결된 송전탑들의 개수를 구하는 함수
def bfs(graph, start, n):
    visited = [False] * (n + 1)  # 방문 여부를 저장하는 리스트 (1번 송전탑부터 n번까지)
    queue = deque([start])  # BFS에 사용할 큐를 초기화하고 시작 노드를 추가
    visited[start] = True  # 시작 노드를 방문 처리
    count = 1  # 현재 네트워크에 속한 송전탑의 개수 (처음 시작 노드를 포함해서 1개)

    # BFS 탐색 시작
    while queue:
        node = queue.popleft()  # 큐에서 노드를 하나 꺼내고
        # 현재 노드에 연결된 이웃 노드들을 확인
        for neighbor in graph[node]:
            if not visited[neighbor]:  # 아직 방문하지 않은 이웃 노드가 있다면
                visited[neighbor] = True  # 방문 처리
                queue.append(neighbor)  # 그 이웃 노드를 큐에 추가
                count += 1  # 이 노드를 포함한 네트워크의 송전탑 개수를 증가시킴
    
    return count  # 네트워크에 속한 송전탑의 총 개수 반환

# 송전탑 네트워크에서 전선을 끊어 네트워크를 나눴을 때 송전탑 개수 차이의 최소값을 구하는 함수
def solution(n, wires):
    # 송전탑 네트워크를 그래프로 표현하기 위해 인접 리스트 사용
    graph = defaultdict(list)
    for v1, v2 in wires:
        # 각 송전탑이 연결된 정보를 그래프에 추가
        graph[v1].append(v2)
        graph[v2].append(v1)
    
    min_diff = float('inf')  # 송전탑 개수 차이의 최소값을 기록할 변수, 처음엔 무한대 값으로 설정

    # 모든 전선을 한 번씩 끊어보면서 두 개의 네트워크로 나눠보기
    for v1, v2 in wires:
        # v1과 v2 사이의 전선을 끊는다 (즉, 그래프에서 해당 연결을 제거)
        graph[v1].remove(v2)
        graph[v2].remove(v1)
        
        # 전선을 끊었을 때 v1이 포함된 네트워크의 송전탑 개수를 구한다
        count1 = bfs(graph, v1, n)
        # 나머지 네트워크의 송전탑 개수는 전체에서 count1을 빼면 된다
        count2 = n - count1
        
        # 두 네트워크의 송전탑 개수 차이의 절대값을 구하고, 최소값을 갱신
        min_diff = min(min_diff, abs(count1 - count2))
        
        # 끊었던 전선을 다시 복구한다 (다음 반복에서 다른 전선을 끊을 수 있도록)
        graph[v1].append(v2)
        graph[v2].append(v1)
    
    return min_diff  # 최소 송전탑 개수 차이 반환