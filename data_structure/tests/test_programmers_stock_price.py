import unittest
from ..csh_programmers_stock_price import *

class TestSolution(unittest.TestCase):
    def setUp(self):
        # 전체 테스트 케이스 작성
        self.test_cases = [
            {
                'creator': 'csh',
                'prices': [500, 200],
                'expected_output': [1, 0]
            },
            {
                'creator': 'csh',
                'prices': [1, 2, 3, 2, 3],
                'expected_output': [4, 3, 1, 1, 0]
            },
        ]
    

    # csh가 작성한 함수를 테스트
    def test_csh(self):
        test_cases = [case for case in self.test_cases if case['creator'] == 'csh']
        for i, case in enumerate(test_cases):
            prices = case['prices']
            expected_output = case['expected_output']
            
            with self.subTest(f"test_case_{i+1}"):
                self.assertEqual(csh_stock_price_solution(prices), expected_output)


if __name__ == "__main__":
    unittest.main(verbosity=2)
