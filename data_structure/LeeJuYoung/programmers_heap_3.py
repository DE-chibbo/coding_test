import heapq

def solution(operations):
    # 최소 힙과 최대 힙을 사용하여 이중 우선순위 큐를 구현
    min_heap = []
    max_heap = []
    entry_finder = {}
    counter = 0  # 고유 id를 부여하여 각 원소의 고유성을 유지

    for operation in operations:
        if operation.startswith('I '):
            # 숫자 삽입 연산
            num = int(operation[2:])
            heapq.heappush(min_heap, (num, counter))
            heapq.heappush(max_heap, (-num, counter))
            entry_finder[counter] = num
            counter += 1

        elif operation == 'D 1' and max_heap:
            # 최댓값 삭제 연산
            while max_heap and max_heap[0][1] not in entry_finder:
                heapq.heappop(max_heap)
            if max_heap:
                _, max_id = heapq.heappop(max_heap)
                del entry_finder[max_id]

        elif operation == 'D -1' and min_heap:
            # 최솟값 삭제 연산
            while min_heap and min_heap[0][1] not in entry_finder:
                heapq.heappop(min_heap)
            if min_heap:
                _, min_id = heapq.heappop(min_heap)
                del entry_finder[min_id]

        # 힙의 유효성을 유지하기 위해 서로 다른 힙에서 제거된 요소들을 동기화
        while max_heap and max_heap[0][1] not in entry_finder:
            heapq.heappop(max_heap)
        while min_heap and min_heap[0][1] not in entry_finder:
            heapq.heappop(min_heap)

    # 큐가 비어있는지 확인
    if not entry_finder:
        return [0, 0]

    # 최댓값과 최솟값을 반환
    while max_heap and max_heap[0][1] not in entry_finder:
        heapq.heappop(max_heap)
    while min_heap and min_heap[0][1] not in entry_finder:
        heapq.heappop(min_heap)
        
    return [-max_heap[0][0], min_heap[0][0]]