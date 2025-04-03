package main

import (
	"fmt"
	"net/http"
)

func main() {
	fmt.Println("Server is running on port 8080")
	http.Handle("/", http.FileServer(http.Dir(".")))
	http.Handle("/Test", Test())
	http.ListenAndServe(":8080", nil)
}

func Test() http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintln(w, "Hello World")
	})
}
