Action Mailer allows you to send emails from your application using mailer classes and views.

### Steps
1. Generate mailer using **rails cli**. 
2. `rails g mailer crud_notification create_notification update_notification delete_notification`
3. I will generate a mailer named *crud_notification* with 3 actions
	1. `create_notification`
	2. `update_notification`
	3. `delete_notification`
4. In the Mailer Action method define the class varibles which will be used in view.
```rb
class CrudNotificationMailer < ApplicationMailer
  
  def create_notification(object)
    @object = object
    @object_count = object.class.count

    mail to: 'admin@example.com', subject: "A new entry for #{object.class} has been created"
  end
  
end

```


5. In the views folder define the template of the mailer actions
`views/crud_notification/create_notification.html.erb`
```html.erb
<h1>Hello Admin User</h1>

<p>
  A new <%= @object.class.to_s %> has been created. Below is the deatails of the object created:
</p>

<% @object.attributes.each do |attr_key, attr_val| %>
  <p><strong><%= attr_key %>: <%= attr_val %></strong></p>
<% end %>

<h4>Now the <%= @object.class.to_s  %> has total of <%= @object_count %> records. </h4>
<h4>Thanks</h4>
<h4>Admin Team</h4>
```

6. In our main controllers add this line when we want to send mail.
`CrudNotificationMailer.create_notification(@user).deliver_now`

```rb
class UsersController < ApplicationController
  def register
    @user = User.create(user_params)
    if @user.valid?
	  # SO whenever a new user is created a mail will be send
      CrudNotificationMailer.create_notification(@user).deliver_now
      token = encode_token({ user_id: @user.id }, Time.now.to_i + 120)
      render json: { user: @user, token: token }, status: :ok
    else
      render json: { error: @user.errors }, status: :unprocessable_entity
    end
  end
end
```

7. **OPTIONAL** : To view the preview add `gem letter_opener` in *Gemfile*.
8. Add config of letter opener in `config/environments/development.rb`
