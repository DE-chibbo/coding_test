import heapq

def solution(jobs):
    # 작업들을 요청 시점을 기준으로 정렬
    jobs.sort()
    
    # 현재 시간
    current_time = 0
    # 대기열 (우선순위 큐)
    waiting_queue = []
    # 작업을 완료하는 데 걸린 시간 합계
    total_time = 0
    # 처리된 작업 수
    completed_jobs = 0
    # jobs 배열에서 작업을 가리킬 인덱스
    job_index = 0
    # 전체 작업 수
    num_jobs = len(jobs)
    
    while completed_jobs < num_jobs:
        # 현재 시간까지 요청된 모든 작업을 대기열에 추가
        while job_index < num_jobs and jobs[job_index][0] <= current_time:
            # (작업 소요 시간, 요청 시점)으로 우선순위 큐에 추가
            heapq.heappush(waiting_queue, (jobs[job_index][1], jobs[job_index][0]))
            job_index += 1
        
        if waiting_queue:
            # 소요 시간이 가장 짧은 작업을 대기열에서 꺼내 처리
            job_duration, job_start = heapq.heappop(waiting_queue)
            # 현재 시간을 해당 작업이 끝나는 시간으로 갱신
            current_time += job_duration
            # 해당 작업이 요청된 시점부터 완료된 시점까지의 시간 계산
            total_time += current_time - job_start
            # 처리된 작업 수 증가
            completed_jobs += 1
        else:
            # 대기열이 비어있으면 시간을 다음 작업의 요청 시점으로 이동
            current_time = jobs[job_index][0]
    
    # 평균 시간을 구하고 소수점 이하는 버림
    return total_time // num_jobs

