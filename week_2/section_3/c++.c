#include <iostream>

int main() {
    int* heapInt = new int(1);
    std::cout << "Heap int: " << *heapInt << std::endl;

    delete heapInt;

    heapInt = nullptr;

    return 0;
}
