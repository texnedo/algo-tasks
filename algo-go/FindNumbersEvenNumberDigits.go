package main

import "fmt"

func swap(x, y string) (string, string) {
	return y, x
}

func main() {
	fmt.Println("Hello, 世界")
	fmt.Print(swap("a", "b"))
}