# 프로그래머스 고득점 Kit - 힙 - 이중 우선순위 큐

import heapq

def solution(operations):
    heap = []
    for i in operations:
        op = i.split(' ')
        if op[0] == 'I':
            heapq.heappush(heap, int(op[1]))
        else:
            if len(heap) <= 0:
                continue
            if op[1] == "1":
                heap.remove(max(heap))
                heapq.heapify(heap)
            if op[1] == "-1":
                heapq.heappop(heap)
    if len(heap) <= 0 :
        return [0, 0]
    return [max(heap), min(heap)]