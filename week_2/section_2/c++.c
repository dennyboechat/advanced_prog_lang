#include <iostream>
#include <functional>

std::function<int()> createCounter(int start = 0) {
    int count = start;

    return [count]() mutable {
        count += 1;
        return count;
    };
}

int main() {
    auto counterA = createCounter();
    auto counterB = createCounter(10);

    std::cout << counterA() << std::endl;
    std::cout << counterA() << std::endl;
    std::cout << counterB() << std::endl;
    std::cout << counterB() << std::endl;

    return 0;
}
