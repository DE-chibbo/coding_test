import unittest
from ..csh_programmers_double_prior_queue import *

class TestSolution(unittest.TestCase):
    def setUp(self):
        # 전체 테스트 케이스 작성
        self.test_cases = [
            {
                'creator': 'csh',
                'operations': ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"],
                'expected_output': [0,0]
            },
        ]
    

    # csh가 작성한 함수를 테스트
    def test_csh(self):
        test_cases = [case for case in self.test_cases if case['creator'] == 'csh']
        for i, case in enumerate(test_cases):
            operations = case['operations']
            expected_output = case['expected_output']
            
            with self.subTest(f"test_case_{i+1}"):
                self.assertEqual(csh_double_prior_queue_solution(operations), expected_output)


if __name__ == "__main__":
    unittest.main(verbosity=2)
