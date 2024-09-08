from collections import deque

def solution(priorities, location):
    # 큐 생성, (우선순위, 인덱스) 형태로 저장
    queue = deque([(priority, idx) for idx, priority in enumerate(priorities)])
    order = 0  # 실행 순서
    
    while queue:
        # 큐에서 첫번째 프로세스 꺼내기
        current = queue.popleft()
        
        # 현재 프로세스보다 우선순위가 높은 프로세스가 있는지 확인
        if any(current[0] < p[0] for p in queue):
            # 우선순위 높은 프로세스가 있으면 다시 큐에 넣음
            queue.append(current)
        else:
            # 그렇지 않으면 실행 (순서 증가)
            order += 1
            if current[1] == location:
                return order  # 찾는 프로세스가 실행된 경우