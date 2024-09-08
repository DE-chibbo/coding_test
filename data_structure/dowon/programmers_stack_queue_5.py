def solution(bridge_length, weight, truck_weights):
    answer = 0
    trucks = truck_weights  # 대기 트럭
    bridge = [0] * bridge_length     # 다리 위 트럭
    current_weight = 0  # 현재 트럭 총 무게
    
    while trucks or current_weight > 0:# O(n)
        answer += 1
        current_weight -= bridge.pop(0)	# O(bridge_length)
        if trucks:
            if current_weight + trucks[0] <= weight:
                truck = trucks.pop(0)
                bridge.append(truck)
                current_weight += truck
            else:
                bridge.append(0)
    return answer