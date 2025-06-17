package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type DataItem struct {
	ID    int
	Value string
}

type Result struct {
	ItemID      int
	WorkerID    int
	Output      string
	ProcessedAt time.Time
}

type SharedQueue struct {
	items []DataItem
	mu    sync.Mutex
}

func (q *SharedQueue) Enqueue(item DataItem) {
	q.mu.Lock()
	defer q.mu.Unlock()
	q.items = append(q.items, item)
}

func (q *SharedQueue) Dequeue() (DataItem, bool) {
	q.mu.Lock()
	defer q.mu.Unlock()

	if len(q.items) == 0 {
		return DataItem{}, false
	}

	item := q.items[0]
	q.items = q.items[1:]
	return item, true
}

func (q *SharedQueue) Size() int {
	q.mu.Lock()
	defer q.mu.Unlock()
	return len(q.items)
}

type ResultStore struct {
	results []Result
	mu      sync.Mutex
}

func (rs *ResultStore) Add(result Result) {
	rs.mu.Lock()
	defer rs.mu.Unlock()
	rs.results = append(rs.results, result)
}

func (rs *ResultStore) GetAll() []Result {
	rs.mu.Lock()
	defer rs.mu.Unlock()

	// Return a copy to avoid concurrent access issues
	resultsCopy := make([]Result, len(rs.results))
	copy(resultsCopy, rs.results)
	return resultsCopy
}

func worker(id int, queue *SharedQueue, resultStore *ResultStore, wg *sync.WaitGroup) {
	defer wg.Done()

	fmt.Printf("Worker %d started\n", id)

	for {
		item, ok := queue.Dequeue()
		if !ok {
			// No more items in the queue
			break
		}

		// Simulate processing time (between 100-300ms)
		processingTime := 100 + rand.Intn(200)
		time.Sleep(time.Duration(processingTime) * time.Millisecond)

		result := Result{
			ItemID:      item.ID,
			WorkerID:    id,
			Output:      fmt.Sprintf("Processed %s by worker %d", item.Value, id),
			ProcessedAt: time.Now(),
		}

		resultStore.Add(result)

		fmt.Printf("Worker %d processed item %d\n", id, item.ID)
	}

	fmt.Printf("Worker %d finished\n", id)
}

func main() {
	// Seed the random number generator
	rand.Seed(time.Now().UnixNano())

	numWorkers := 5
	numItems := 20

	fmt.Printf("Starting Data Processing System with %d workers and %d items\n", numWorkers, numItems)

	queue := &SharedQueue{items: []DataItem{}}
	resultStore := &ResultStore{results: []Result{}}

	for i := 1; i <= numItems; i++ {
		queue.Enqueue(DataItem{
			ID:    i,
			Value: fmt.Sprintf("Item-%d", i),
		})
	}

	fmt.Printf("Queue loaded with %d items\n", queue.Size())

	var wg sync.WaitGroup

	startTime := time.Now()
	for i := 1; i <= numWorkers; i++ {
		wg.Add(1)
		go worker(i, queue, resultStore, &wg)
	}

	wg.Wait()
	elapsedTime := time.Since(startTime)

	results := resultStore.GetAll()
	fmt.Printf("\nProcessing complete. Took %v\n", elapsedTime)
	fmt.Printf("Processed %d items\n", len(results))

	workerCounts := make(map[int]int)
	for _, r := range results {
		workerCounts[r.WorkerID]++
	}

	fmt.Println("\nWork distribution:")
	for workerID, count := range workerCounts {
		fmt.Printf("Worker %d: processed %d items (%.1f%%)\n",
			workerID, count, float64(count)/float64(len(results))*100)
	}

	fmt.Println("\nSample results:")
	numToShow := 5
	if numToShow > len(results) {
		numToShow = len(results)
	}

	for i := 0; i < numToShow; i++ {
		r := results[i]
		fmt.Printf("Item %d: %s at %v\n",
			r.ItemID, r.Output, r.ProcessedAt.Format("15:04:05.000"))
	}
}
