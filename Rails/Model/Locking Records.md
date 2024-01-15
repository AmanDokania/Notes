## 1. Optimistic Locking
- Multiple users can access the record for edit
- It assumes minimum conflicts.
#### Steps
1. Create a **locking column** in the table
2. **Define locking column** in Model.
```rb
class Customer < ApplicationRecord
  self.locking_column = :lock_customer_column
end
```

## 2. Pessimistic Locking
- It uses **locking mechanism** provided by the **database**
- Wrapped inside a **transaction** to avoid **deadlock**
```rb
Book.transaction do
  book = Book.lock.first
  book.title = 'Algorithms, second edition'
  book.save!
end
```

**LOCK IN SHARE MODE** : To enable reading of locked data
```rb
Book.transaction do
  book = Book.lock("LOCK IN SHARE MODE").find(1)
  book.increment!(:views)
end
```
