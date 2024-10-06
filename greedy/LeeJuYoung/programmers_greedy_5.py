def solution(n, costs):
    # 간선을 비용 기준으로 오름차순 정렬
    costs.sort(key=lambda x: x[2])
    
    parent = [i for i in range(n)]  # 초기에는 각 섬이 자기 자신을 부모로 가짐
    
    # 부모 노드를 찾는 함수 (경로 압축 기법 적용)
    def find(x):
        if parent[x] != x:
            parent[x] = find(parent[x])
        return parent[x]
    
    # 두 노드를 합치는 함수
    def union(x, y):
        x_root = find(x)
        y_root = find(y)
        if x_root != y_root:
            parent[y_root] = x_root  # y의 루트 부모를 x의 루트 부모로 설정
    
    total_cost = 0  # 총 비용
    for x, y, cost in costs:
        # 두 노드의 부모가 다르면 사이클이 발생하지 않으므로 간선 추가
        if find(x) != find(y):
            union(x, y)
            total_cost += cost  # 비용 추가
    
    return total_cost
