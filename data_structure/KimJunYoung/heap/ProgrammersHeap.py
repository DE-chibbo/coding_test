import heapq


def solution(operations):
    dpq = DoublePriorityQueue()
    for operation in operations:
        op_type = operation[0]
        if op_type == 'I':
            value = int(operation[2:])
            dpq.push(value)
            continue
        if op_type == 'D':
            pick_type = int(operation[2:])
            if pick_type == 1:
                dpq.pop_max()
            elif pick_type == -1:
                dpq.pop_min()

    return dpq.get_max_min()


class DoublePriorityQueue:
    def __init__(self):
        self.asc_queue = []
        self.desc_queue = []

    def push(self, value):
        heapq.heappush(self.asc_queue, value)

    def pop_max(self):
        while self.asc_queue:
            heapq.heappush(self.desc_queue, -heapq.heappop(self.asc_queue))

        if self.desc_queue:
            return -heapq.heappop(self.desc_queue)

        return -1

    def pop_min(self):
        while self.desc_queue:
            heapq.heappush(self.asc_queue, -heapq.heappop(self.desc_queue))

        if self.asc_queue:
            return heapq.heappop(self.asc_queue)

        return -1

    def get_max_min(self):
        if not self.asc_queue and not self.desc_queue:
            return [0, 0]

        max_min = []
        while self.asc_queue:
            heapq.heappush(self.desc_queue, -heapq.heappop(self.asc_queue))
        if self.desc_queue:
            max_min.append(-self.desc_queue[0])

        while self.desc_queue:
            heapq.heappush(self.asc_queue, -heapq.heappop(self.desc_queue))
        if self.asc_queue:
            max_min.append(self.asc_queue[0])

        return max_min


print(solution(["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]))
print(solution(["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]))
print(solution(["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333", "D -1", "D 1"]))
