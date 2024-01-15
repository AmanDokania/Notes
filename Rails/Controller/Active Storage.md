	1. First install active storage
1. `rails active_storage:install`
2. It will generate a migration where we will store our metadata
3. Run the migration
4. Set up the relationship by adding `has_one_attached: <cover_image>` in the model.
5. Now to upload the image add `form.file_field` in the form view paratial
```html.erb

  <div>
  <%= form.label :cover_picture %>
  <%= form.file_field :cover_picture %>
  </div>

```
7. Now to view the image:
8. `<%= image_tag @article.cover_picture, style: "width: 400px" %>`
9. TO remove a file from a model use `@article.cover_picture.purge`

**NOTE**: To check if a file is attached to model or not use `@article.cover_picture.attached?`

### has_many_attached
```rb
class Message < ApplicationRecord
  has_many_attached :images
end
```

### To attach from IO
```rb
@message.image.attach(
  io: File.open('/path/to/file'),
  filename: 'file.pdf',
  content_type: 'application/pdf',
  identify: false
)
```

### To remove => `@article.cover_image.purge`
