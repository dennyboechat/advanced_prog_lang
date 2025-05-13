function createCounter(start = 0) {
    let count = start;

    function counter() {
        count += 1;
        return count;
    }

    return counter;
}

const counterA = createCounter();
const counterB = createCounter(10);

console.log(counterA());
console.log(counterA());
console.log(counterB());
console.log(counterB());
