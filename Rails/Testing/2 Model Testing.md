1. Generate for model we want to test 
2.  `rails g rspec:model article`
3. Add `faker` gem in Gemfile *(optional)*
	1. Now fill the dummy data in `factories/model_name.rb` file
```rb
FactoryBot.define do
  factory :article do
    title { Faker::Book.title }
    body { Faker::Book.genre }
  end
end
```
5. In model_spec file write the testcase like this
```rb
require 'rails_helper'

RSpec.describe Article, type: :model do
  context 'when creating an article' do
    # It will create a new method named article which when called return article object
    let(:article) { build :article }

    # logic we want to perform
    it 'should be valid user with all attributes' do
      expect(article.valid?).to eq(true)
    end
  end
end
```

6. If we want to create a object of model we can also sue `FactoryBot.create(:modelName)`

#### To create an object with custom value
```rb
let(:article) { create :article, body : 'YOUR CUSTOM VALUE' }
```

- To run a testcase depending on the line number we can write.
- `rspec spec/models/article_spec.rb:12` <- Line number

For **error** testing use `{ }` instead of `()`
```rb
    it 'should give error for duplication' do
      article.save

      expect(article3.save).to eq(false)
      expect { article3.save! }.to raise_error(ActiveRecord::RecordInvalid)
    end
```

#### To Test Custom Boolean methods
use 
- `to be_<method_name>`
```rb
  describe '#god?' do
  
      it 'should identify god' do
        dummy = build(:article, title: 'God')
        expect(dummy).to be_god
      end

      it 'should not be god' do
        dummy = build(:article, title: 'Satan')
        expect(dummy).not_to be_god
      end
    end
```

#### describe is used to group similar testcases