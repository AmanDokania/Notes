1. Using a **String SQL Fragement**
```rb
Author.joins("INNER JOIN books ON books.author_id = authors.id AND books.out_of_print = FALSE")
```

2. Join **single assciation**
```rb
Book.joins(:reviews)
```

3. Join **multiple association**
```rb
Book.joins(:author, :reviews)
```

4. **Nested** Join
```rb
Book.joins(reviews: :customer)

#SELECT books.* FROM books
#  INNER JOIN reviews ON reviews.book_id = books.id
#  INNER JOIN customers ON customers.id = reviews.customer_id

```

5. **Left Outer Join** to get record whether they have assciated data present or not.
```rb
Customer.left_outer_joins(:reviews).distinct.select('customers.*, COUNT(reviews.*) AS reviews_count').group('customers.id')
```


