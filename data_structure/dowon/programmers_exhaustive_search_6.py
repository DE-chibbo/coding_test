# 프로그래머스 고득점 Kit - 완전탐색 - 전력망을 둘로 나누기

from collections import deque

def solution(n, wires):
    res = 0  # 결과를 저장할 변수 초기화
    graph = [[] for _ in range(n + 1)]  # 그래프 초기화 (1부터 n까지)

    # 주어진 전선 정보를 그래프에 추가
    for a, b in wires:
        graph[a].append(b)
        graph[b].append(a)
    
    # BFS 함수 정의
    def bfs(start):
        visited = [0] * (n + 1)  # 방문 여부 리스트 초기화
        q = deque([start])  # BFS를 위한 큐 초기화
        visited[start] = 1  # 시작 노드 방문 처리
        cnt = 1  # 시작 노드를 포함한 카운트
        
        while q:
            s = q.popleft()  # 큐에서 노드 하나를 꺼냄
            for i in graph[s]:  # 현재 노드와 연결된 노드 탐색
                if not visited[i]:  # 방문하지 않은 노드라면
                    q.append(i)  # 큐에 추가
                    visited[i] = 1  # 방문 처리
                    cnt += 1  # 카운트 증가
        return cnt  # 현재 노드를 포함한 연결된 노드의 수 반환
            
    res = n  # 결과 초기값을 n으로 설정 (최대 차이는 n - 1일 수 있음)
    
    # 모든 전선에 대해 반복
    for a, b in wires:
        graph[a].remove(b)  # 전선 a-b 제거
        graph[b].remove(a)  # 전선 b-a 제거
        
        # 두 부분으로 나누어진 그래프의 노드 수 차이를 계산
        res = min(abs(bfs(a) - bfs(b)), res)  # 두 부분의 노드 수 차이의 최솟값을 업데이트
        
        # 전선을 다시 추가하여 원래 상태로 복구
        graph[a].append(b)
        graph[b].append(a)
    
    return res  # 최종 결과 반환
