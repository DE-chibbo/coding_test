import unittest
from ..csh_programmers_room_count import *

class TestSolution(unittest.TestCase):
    def setUp(self):
        # 전체 테스트 케이스 작성
        self.test_cases = [
            {
                'creator': 'csh',
                'arrows': [6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0],
                'expected_output': 3
            },
        ]
    

    # csh가 작성한 함수를 테스트
    def test_csh(self):
        test_cases = [case for case in self.test_cases if case['creator'] == 'csh']
        for i, case in enumerate(test_cases):
            arrows = case['arrows']
            expected_output = case['expected_output']
            
            with self.subTest(f"test_case_{i+1}"):
                self.assertEqual(csh_room_count_solution(arrows), expected_output)


if __name__ == "__main__":
    unittest.main(verbosity=2)
