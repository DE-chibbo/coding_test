def solution(prices):
    answer = [0] * len(prices)
    stack = []
    for i in range(len(prices)):
        while len(stack) != 0 and stack[-1][1] > prices[i]:
            pastTime = stack.pop()[0]
            answer[pastTime] = i - pastTime
        stack.append([i, prices[i]])

    for time, price in stack:
        answer[time] = len(answer) - 1 - time

    return answer