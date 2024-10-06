def solution(number, k):
    stack = []
    for num in number:
        # 제거해야 할 숫자가 남아 있고, 스택이 비어 있지 않으며,
        # 스택의 마지막 숫자가 현재 숫자보다 작으면
        while k > 0 and stack and stack[-1] < num:
            stack.pop()
            k -= 1
        stack.append(num)
    # 만약 제거해야 할 숫자가 남아 있으면 뒤에서부터 제거
    if k > 0:
        stack = stack[:-k]
    return ''.join(stack)
