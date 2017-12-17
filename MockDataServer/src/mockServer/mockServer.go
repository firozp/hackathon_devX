package main

import (
	"fmt"
	"net/http"
)

func main() {
	fs := http.FileServer(http.Dir("/mock/data/"))
  	http.Handle("/static/", http.StripPrefix("/static/", fs))
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Hello! try /static/<filename>\n")
	})
  	fmt.Println("Listening...")
	http.ListenAndServe(":8080", nil)
}
