zIt is another way to render views in rails.

### Steps to follow
1. Add `gem "jbuilder"` in **Gemfile**.
2. Now in the views folder create file according to controller
*For eg*
	- Our cotroller name is `user_controller.rb` and action name is `login`
	- So create a folder in `views` folder named `users` and create a file named `login.json.jbuilder`
```rb
json.data do
  json.user do
    json.call(
      @user,
      :id,
      :username
    )
  end
  json.token @token
end
```

> Note: We cannot access local variables in views so we need to add `@` in variables we want to use in our jbuilder views.

#### To Create an array in Jbuilder
```rb
json.array! @todos do |todo|
  json.customIdName todo.id
  json.customTitleName todo.title
end

{
	[
		{
		"customIdName" : 1
		"customTitleName" : "Kushal"
		},
		{
		"customIdName" : 2
		"customTitleName" : "Ben"
		},
		{
		"customIdName" : 2
		"customTitleName" : "Sina"
		},
	]
}

```

#### Extract attributes from the object
```rb
json.extract! @article, :id, :title

{
	"id": 1,
	"title": "Environments" 
}
```


#### Conditional View
```rb
json.extract! @article, :id, :title
json_edit_url edit_article_url(@article) if current_user.admin?
{
	"id": 1,
	"title": "Environments" 
	"edit_url": "http//localhost:3000/article/1/edit"
}
```

