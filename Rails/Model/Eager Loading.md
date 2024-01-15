- Loading assciated records using **find** .
- Require fewer syntax to write.

We can specify in advance what associations are required
#### 1. includes
> If we do not use it it will query the database for every loop iteration to feth author records for each book
```rb
books = Book.includes(:author).limit(10)

books.each do |book|
  puts book.author.last_name
end

```

#### 2. preload
- It specifies association using one query
```rb
books = Book.preload(:author).limit(10)

books.each do |book|
  puts book.author.last_name
end
```
```sql
SELECT books.* FROM books LIMIT 10
SELECT authors.* FROM authors
  WHERE authors.book_id IN (1,2,3,4,5,6,7,8,9,10)

```

#### 3. eager_load
- With `eager_load`, Active Record loads all specified associations using a `LEFT OUTER JOIN`.
```rb
books = Book.eager_load(:author).limit(10)

books.each do |book|
  puts book.author.last_name
end
```
```sql
SELECT DISTINCT books.id FROM books LEFT OUTER JOIN authors ON authors.book_id = books.id LIMIT 10
SELECT books.id AS t0_r0, books.last_name AS t0_r1, ...
  FROM books LEFT OUTER JOIN authors ON authors.book_id = books.id
  WHERE books.id IN (1,2,3,4,5,6,7,8,9,10)

```
