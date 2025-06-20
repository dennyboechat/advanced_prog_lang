#include <stdio.h>
#include <stdlib.h>

double calculate_mean(int arr[], int size);
double calculate_median(int arr[], int size);
void calculate_mode(int arr[], int size);
void sort_array(int arr[], int size);
void print_array(int arr[], int size);

int main() {
    int size;
    
    printf("Enter the number of integers: ");
    scanf("%d", &size);
    
    if (size <= 0) {
        printf("Error: Number of integers should be greater than 0.\n");
        return 1;
    }
    
    int *numbers = (int *)malloc(size * sizeof(int));
    if (numbers == NULL) {
        printf("Error: Memory allocation failed.\n");
        return 1;
    }
    
    printf("Enter %d integers:\n", size);
    for (int i = 0; i < size; i++) {
        scanf("%d", &numbers[i]);
    }
    
    printf("\nOriginal array: ");
    print_array(numbers, size);
    
    double mean = calculate_mean(numbers, size);
    printf("\nMean: %.2f\n", mean);
    
    sort_array(numbers, size);
    
    printf("Sorted array: ");
    print_array(numbers, size);
    
    double median = calculate_median(numbers, size);
    printf("\nMedian: %.2f\n", median);
    
    calculate_mode(numbers, size);
    
    free(numbers);
    
    return 0;
}

/**
 * Calculates the mean (average) of the integers in the array.
 *
 * @param arr The array of integers.
 * @param size The size of the array.
 * @return The mean as a double.
 */
double calculate_mean(int arr[], int size) {
    double sum = 0;
    
    for (int i = 0; i < size; i++) {
        sum += arr[i];
    }
    
    return sum / size;
}

/**
 * Calculates the median of the integers in the array.
 * The array should be sorted before calling this function.
 *
 * @param arr The sorted array of integers.
 * @param size The size of the array.
 * @return The median as a double.
 */
double calculate_median(int arr[], int size) {
    // If the array has an odd number of elements
    if (size % 2 != 0) {
        return arr[size / 2];
    }
    // If the array has an even number of elements
    else {
        return (arr[(size - 1) / 2] + arr[size / 2]) / 2.0;
    }
}

/**
 * Calculates and prints the mode(s) of the integers in the array.
 * The array should be sorted before calling this function for efficiency.
 *
 * @param arr The array of integers.
 * @param size The size of the array.
 */
void calculate_mode(int arr[], int size) {
    if (size <= 0) {
        printf("Mode: No mode (empty array)\n");
        return;
    }
    
    int max_count = 0;
    int current_count = 1;
    int mode_count = 0;
    
    for (int i = 1; i < size; i++) {
        if (arr[i] == arr[i - 1]) {
            current_count++;
        } else {
            if (current_count > max_count) {
                max_count = current_count;
            }
            current_count = 1;
        }
    }
    
    if (current_count > max_count) {
        max_count = current_count;
    }
    
    if (max_count == 1) {
        printf("Mode: No mode (all elements appear exactly once)\n");
        return;
    }
    
    printf("Mode: ");
    
    current_count = 1;
    for (int i = 1; i < size; i++) {
        if (arr[i] == arr[i - 1]) {
            current_count++;
        } else {
            if (current_count == max_count) {
                printf("%d ", arr[i - 1]);
                mode_count++;
            }
            current_count = 1;
        }
    }
    
    if (current_count == max_count) {
        printf("%d ", arr[size - 1]);
        mode_count++;
    }
    
    printf("(appeared %d times)\n", max_count);
}

/**
 * Sorts the array in ascending order using bubble sort.
 *
 * @param arr The array of integers to be sorted.
 * @param size The size of the array.
 */
void sort_array(int arr[], int size) {
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap elements
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

/**
 * Prints the array elements.
 *
 * @param arr The array of integers.
 * @param size The size of the array.
 */
void print_array(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}
