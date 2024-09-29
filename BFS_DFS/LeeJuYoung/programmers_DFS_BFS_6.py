from collections import defaultdict

def solution(tickets):
    graph = defaultdict(list)
    
    # 그래프 생성 및 도착지 리스트 내림차순 정렬
    for a, b in tickets:
        graph[a].append(b)
    for key in graph.keys():
        graph[key].sort(reverse=True)
    
    route = []
    stack = ["ICN"]  # 시작 공항
    
    while stack:
        top = stack[-1]
        # 현재 공항에서 출발하는 항공권이 있는 경우
        if graph[top]:
            next_dest = graph[top].pop()
            stack.append(next_dest)
        else:
            # 더 이상 사용할 항공권이 없는 경우 경로에 추가
            route.append(stack.pop())
    
    # 경로를 뒤집어서 반환
    return route[::-1]
