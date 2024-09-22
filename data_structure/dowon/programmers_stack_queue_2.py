def solution(progresses, speeds):
    answer = []
    while True: #최악의 경우 O(n)
        cnt = 0
        if progresses[0] < 100:
            progresses = [progresses[i] + speeds[i] for i in range(len(progresses))] # O(n)
        else :
            for k in range(len(progresses)): # O(n)
                if progresses[k] >= 100:
                    cnt += 1
                else:
                    break
            del progresses[:cnt] # O(n)
            del speeds[:cnt] # O(n)
            answer.append(cnt)
        if len(progresses) == 0 : # 작업 완료
            break
    return answer