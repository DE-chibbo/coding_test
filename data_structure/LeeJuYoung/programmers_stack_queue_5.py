from collections import deque

def solution(bridge_length, weight, truck_weights):
    bridge = deque([0] * bridge_length)  # 다리 위에 있는 트럭을 관리 (현재는 빈 상태)
    truck_weights = deque(truck_weights)  # 대기 트럭 리스트
    time = 0  # 경과 시간
    current_weight = 0  # 현재 다리 위에 있는 트럭들의 무게 합
    
    while bridge:
        time += 1
        # 다리에서 트럭이 빠져나가면 무게 감소
        current_weight -= bridge.popleft()
        
        if truck_weights:
            # 다음 트럭이 올라갈 수 있는지 확인
            if current_weight + truck_weights[0] <= weight:
                truck = truck_weights.popleft()
                bridge.append(truck)  # 트럭이 다리로 진입
                current_weight += truck  # 다리 위 무게 업데이트
            else:
                bridge.append(0)  # 트럭이 못 올라가면 0을 넣어서 다리 길이 유지
    
    return time