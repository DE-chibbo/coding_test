def solution(n, computers):
    def dfs(node):
        # 현재 노드를 방문 처리
        visited[node] = True
        # 현재 노드와 연결된 다른 노드들을 탐색
        for i in range(n):
            # 연결되어 있고 아직 방문하지 않은 노드일 경우 DFS 재귀 호출
            if computers[node][i] == 1 and not visited[i]:
                dfs(i)

    # 방문 여부를 기록하는 리스트
    visited = [False] * n
    network_count = 0  # 네트워크 개수를 저장할 변수

    # 모든 컴퓨터를 순차적으로 탐색
    for i in range(n):
        # 만약 해당 컴퓨터를 아직 방문하지 않았다면 새로운 네트워크로 간주
        if not visited[i]:
            dfs(i)  # DFS 탐색으로 해당 네트워크의 모든 컴퓨터를 방문 처리
            network_count += 1  # 새로운 네트워크를 발견했으므로 네트워크 수 증가

    return network_count
