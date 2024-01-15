#### 1. find 
**find** : we can find retrieve an object from database by passing the **primary key**.
```rb
# To find a user with id 1
user = User.find(1)

# To find users present in the array
users = User.find([1,2,3,4,5])

```
> **Note:** It will raise an `ActiveRecord::RecordNotFound` error 
---

#### 2. take
**take** : It retrives **one or more records** in no implicit ordering
```rb

user = User.take(1) # retrive one record from the table 

user = User.take(5) #Select * from user LIMIT 5

```
> **NOTE** : It will return *nil* if no record found.

**take!** : will raise error if record not found

---

#### 3. first / last
**first** : retrieve **first record** order by primary key ascending
**last** : retrive **first record** order by primary key desceneding

`first!` and `last!` will raise an error if record not find

---

#### 4. find_by
We can find a **single record** by specifing the attribute name.
```rb
user = User.find_by(name: "Kushal")

#Same as
user = User.where(name: "Kushal").take!

```


## Retrieving Multiple Objects
```rb
# This may consume too much memory if the table is big.
Customer.all.each do |customer|
  NewsMailer.weekly(customer).deliver_now
end

```

To retrive in batches we have.

#### 5. find_each
It retrives the records on **batches** and then yields each one in block.
```rb
Customer.find_each do |customer|
  NewsMailer.weekly(customer).deliver_now
end
```

##### batch_size
We can pass batch size using `batch_size: 5000`
```rb
Customer.find_each(batch_size: 5000) do |customer|
  NewsMailer.weekly(customer).deliver_now
end
```

##### start / finish
We can define **starting point** from where we want to find records and **ending point**.
```rb
Customer.find_each(start: 2000, finish:9000) do |customer|
  NewsMailer.weekly(customer).deliver_now
end
```

---

#### 6. find_in_batches
Same as find_each but it yields block as an array upto 1000 records at a time
```rb
# Give add_customers an array of 1000 customers at a time.
Customer.find_in_batches do |customers|
  export.add_customers(customers)
end
```

##### Some options we can pass
**:batch_size**, **:start**, **:finish**



## Conditions

#### 7. where
It allows to add specific conditions to limit the records returned.

1. **Pure String Condition**
	We can pass string conditions but it will make our application vulnerable for SQL Injection
	```rb
Book.where("title = 'Introduction to Algo'")
```

2. **Array Condtion** `( ? )`
```rb

Book.where("title = ?", params[:title])

# We can use multiple ?
Book.where("title = ? AND out_of_print = ?", params[:title], false)
```

3. **Placeholder Condition** `:key`
```rb
Book.where("created_at >= :start_date AND created_at<= :end_date", {start_date: params[:start_date], end_date: params[:end_date]})
```
**STEPS** : 
	- Pass **string** with string `:keys` 
	- And then **hash** with key and value defined

4. Condition for **LIKE**
```rb
Book.where("title like ?", params[:title] + "%")
```
> NOTE: If `%` or `_` are present in `params[:title]` then it will generate unexpected results. So use `sanitize_sql_like` method.
```rb
Book.where("title like ?", Book.sanitize_sql_like(params[:title] + "%"))
```

5. **Hash Conditions**

Equality Condition
```rb
Book.where(out_of_print: true)
Book.where('out_of_print' => true)
```

Range Condition
```rb
Book.where(created_at: (Time.now.midnight - 1.day)..Time.now.midnight)
```

Lesser / Greater Conditions (*Beginless and endless ranges*)
```rb
Book.where(created_at: (Time.now.midnight - 1.day)..)
```

**Subset Conditions** (Converted to **IN** in sql)
```rb
# IN
Customer.where(orders_count: [1,3,5])

# Not IN
Customer.where.not(orders_count: [1,3,5])
```

**OR Conditions**
```rb
Customer.where(last_name: 'Smith').or(Customer.where(orders_count: [1,3,5]))
```

**And Conditions**
- We can chain `where` for and conditions
- We can also use like we used `or`
```rb
Customer.where(last_name: 'Smith').where(orders_count: [1,3,5]))
```
#### 8. ordering
```rb
Book.order(:created_at)

Book.order(:created_at :desc)
```

**Order by multiple attributes**
We can chain multiple attributes
```rb
Book.order("title ASC").order("created_at DESC")
```

#### 9. Selecting Specific Fields
```rb
Book.select(:isbn, :out_of_print)
# OR
Book.select("isbn, out_of_print")
```

- **distinct**
```rb
Customer.select(:last_name).distinct
```

#### 10. Limit and Offset
```rb
Customer.limit(5).offset(30)
```

#### 11. Group
```rb
Order.select("created_at").group("created_at")
```

#### 12. Count
```rb
Order.select("created_at").group("created_at").count
```

#### 13. Having
```rb
Order.select("created_at, sum(total) as total_price").
  group("created_at").having("sum(total) > ?", 200)

```



#### 14. Overriding Conditions
- **unscope**
To remove a condition use *unscope()*
```rb
# It will remove order condition
Book.where('id > 100').limit(20).order('id desc').unscope(:order)

```

- **only**
TO allow only passed attributes
```rb
Book.where('id > 10').limit(20).order('id desc').only(:order, :where)

# it will remove limit from the query
```

- **reselect**
To change attributes in reselect
```rb
Book.select(:title, :isbn).reselect(:created_at)

# SELECT books.created_at FROM books

```
#### 15. pluck
It can be used to query single or multiple columns from the underlying table of a model.
- It accepts a **list of column names** as an argument 
- Returns an **array of values** of the specified columns with the corresponding data type.