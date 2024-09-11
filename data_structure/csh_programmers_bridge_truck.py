'''
programmers basic test : passed
programmers final test : passed

time complexity : O(nm)
- n : 트럭 수
- m : 다리 길이
'''


def solution(bridge_length, weight, truck_weights):
    answer = 0
    
    bridge_queue = []
    i = 0
    while True:
        answer += 1
        if bridge_queue and answer - bridge_queue[0][1] >= bridge_length:
            bridge_queue.pop(0)
            if i == len(truck_weights) and not bridge_queue:
                break
        
        current_bridge_weight = sum(arr[0] for arr in bridge_queue)
        if i < len(truck_weights) and current_bridge_weight + truck_weights[i] <= weight:
            bridge_queue.append((truck_weights[i], answer))
            i += 1