An _association_ is a connection between two Active Record models.

Rails support six types of assciations
- `belongs_to`
- `has_one`
- `has_many`
- `has_many :through`
- `has_one :through`
- `has_and_belongs_to_many`

### Create One to Many Relation

#### Student - Blogs (One Student Many Blogs)

Student can have many blogs so in blogs Model use `belongs to: student`
In the blogs model we need to create a `student_id` first which will serve as foreign key
```rb
class CreateBlogs < ActiveRecord::Migration[7.0]
  def change
    create_table :blogs do |t|
      t.string :title
      t.text :content
      t.integer :student_id

      t.timestamps
    end
  end
end

```

```rb
class Blog < ApplicationRecord
	belongs_to :student
end
```

In student model use `has_many: blogs`
```rb
class Student < ApplicationRecord
	has_many :blogs
end
```
##### Operations 

- To Create a new blog:
```rb
stu = Student.new do |s|
		s.name = "Kushal"
		s.email = "kushal@gmail.com"
	end

stu.blogs.create(title: "New Generations", content: "This is content of blog" )

```

- To get all blogs of a student : 
```rb
stu = Student.find(1)
stu.blogs
```

#### Summary
1. Create a User Model
2. Create a Blogs Model
3. Add `user_id` column in **Blogs Migration**
4. Add `has_many :blogs` in **Student Model**
5. Add `belongs_to :student` in **Blogs Model**


### Create Many to Many Relation
There are two ways to create many to many relations

#### 1. has_and_belongs_to_many

- `has_and_belongs_to_many` - Creates many to many relationships
- It do not require any interveing model
- But it require to create a **join table** 

##### Steps
1. Create A **Student Model** and **Course Model**
2. In the Student and Course Model add `has_and_belongs_to_many`

**StudentModel** `models/student.rb`
```rb
class Student < ApplicationRecord
    has_many :blogs 
    has_and_belongs_to_many :courses
end
```

**CourseModel** `models/course.rb`
```rb
class Course < ApplicationRecord
    has_and_belongs_to_many :students
end
```

3. Create a migration for join table.
```rb
class CreateCourseStudentJoinTable < ActiveRecord::Migration[7.0]
  def change
    create_join_table :courses, :students 
  end
end
```

4. Run the migration to add this table in our schema. `rails db:migrate`
5. Now we have created many to many relation between Students and Course.

##### Operations
```rb
stu = Student.create(name: "Kushal", email: "kushal@gmail.com", age: 22)

# TO create a course
stu.courses.create(name: "Web Development", description: "Website Development", points: 40)

# Same can be done by course to student.
```

#### Summary
1. Create a student and course model and migrate them too.
2. Add `has_and_belongs_to_many : students/courses` in both the model file.
3. Now create a join table `create_join_table :courses, :students`.
4. Now we have successfully created many to many relationship.

#### 2. has_many: through
- It creates many to many relation.
- It uses a third table but this table also have its model unlike `has_and_belongs_to_many`.

##### Steps
- Generate a student model
- Generate a project model `rails g model project name:string desc:string`
- Run the migration
- Create *one more model* to connect these two models `rails g model student_project student:references project:references`
- Run the migration `rails db:migrate`
- Add association in `Student Model`
```rb
class Student < ApplicationRecord
    has_many :student_projects
    has_many :projects, through: :student_projects
end
```
 - Add association in `Project Model`.
 ```rb
 class Project < ApplicationRecord
  has_many :student_projects
  has_many :projects, :through :student_projects
end
```
- Now we can also add other field in the `student_projects` table
- `rails g migration add_submission_date_to_student_projects submission_date:date`
- Run the migration.
- Now we have created a *many to many* relation with custom columns.
##### Implementation
```rb
# First we create sum projects
Project.create(name: "HealthAPP")
Project.create(name: "Block Chain App")
Project.create(name: "E-Commerce App")

Student_Project.create(student_id: 1, project_id: 1, submission_date: Date.today + 30.days)


stu = Student.find(1)

stu.projects # It will give the list of all projects by kushal

pro.students # It will give all the student

```

### Create One to One Relation
- It can be used in department like one *department* one have one *manager*
##### Steps
- Create a demo model
- Create a sub_demo model `rails g model sub_demo title:string  demo:references`
- Run the migration
- Add the association in `demo model` 
```rb
class Demo < ApplicationRecord
  has_one :sub_demo
end
```

```rb
class SubDemo < ApplicationRecord
  belongs_to :demo
end
```

