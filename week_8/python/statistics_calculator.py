"""
Statistics Calculator Module

"""
from typing import List, Dict, Union, Any
from collections import Counter


class StatisticsCalculator:
    def __init__(self, data: List[int] = None):
        self.data = data if data is not None else []

    def set_data(self, data: List[int]) -> None:
        self.data = data

    def get_data(self) -> List[int]:
        return self.data

    def calculate_mean(self) -> Union[float, None]:
        if not self.data:
            return None
        
        return sum(self.data) / len(self.data)

    def calculate_median(self) -> Union[float, int, None]:
        if not self.data:
            return None
        
        sorted_data = sorted(self.data)
        n = len(sorted_data)
        
        # If the list has odd length
        if n % 2 == 1:
            return sorted_data[n // 2]
        # If the list has even length
        else:
            middle1 = sorted_data[n // 2 - 1]
            middle2 = sorted_data[n // 2]
            return (middle1 + middle2) / 2

    def calculate_mode(self) -> Union[List[int], None]:
        if not self.data:
            return None
        
        counts = Counter(self.data)
        
        max_count = max(counts.values())
        
        if max_count == 1:
            return list(self.data)
        
        return [value for value, count in counts.items() if count == max_count]

    def calculate_all_statistics(self) -> Dict[str, Any]:
        return {
            'mean': self.calculate_mean(),
            'median': self.calculate_median(),
            'mode': self.calculate_mode()
        }


if __name__ == "__main__":
    calculator = StatisticsCalculator([1, 3, 3, 5, 7, 9, 9])
    
    print(f"Data: {calculator.get_data()}")
    print(f"Mean: {calculator.calculate_mean()}")
    print(f"Median: {calculator.calculate_median()}")
    print(f"Mode: {calculator.calculate_mode()}")
    
    print("\nAll statistics:")
    all_stats = calculator.calculate_all_statistics()
    for stat, value in all_stats.items():
        print(f"{stat.capitalize()}: {value}")
