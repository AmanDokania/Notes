 - Add `spec` and `factory_bot` in **Gemfile**
```rb
group :development, :test do
  gem "debug", platforms: %i[ mri mingw x64_mingw ]
  gem "rspec-rails"
  gem "factory_bot_rails"
end
```

- Run `bundle install`
- Run `rails g rspec:install`, It will create 3 spec files.
- Uncomment this line from `spec/rails_helper.rb`
```rb
Dir[Rails.root.join('spec', 'support', '**', '*.rb')].sort.each { |f| require f }
```
- Create a file `spec/support/factory_bot.rb`
```rb
RSpec.configure do |config|
  confi.include FactoryBot::Syntax::Methods
end
```
- Run migrate in testing environment
- `RAILS_ENV=test rails db:migrate`

- In *.rspec* file add this line `--format documentation` for better results when run a test case

## Factory BOT
1. **FactoryBot.create(:user)** it will create and save.
2. **FactoryBot.build(:user)** it will only create.