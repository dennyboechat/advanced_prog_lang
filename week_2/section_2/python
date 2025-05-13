def create_counter(start=0):
    count = start
    
    def counter():
        nonlocal count
        count += 1
        return count
    
    return counter

counter_a = create_counter()
counter_b = create_counter(10)

print(counter_a())  # Output: 1
print(counter_a())  # Output: 2
print(counter_b())  # Output: 11
print(counter_b())  # Output: 12
