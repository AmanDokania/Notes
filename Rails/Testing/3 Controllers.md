1. Create a `controllers` folder in `spec` direcotry
2. Create a spec file `article_controller_spec.rb`
3. Beacuse controller testing like this is deprecated we need to add this gem `  gem "rails-controller-testing"
4. We will use **request spec**`
```rb
require 'rails_helper'

RSpec.describe ArticlesController, type: :controller do
  describe 'GET index' do
    let(:article) { create :article }
    # <================ Testing the get index ==================>
    it 'assigns @articles' do
      get :index # defining the controller method we want to test
      expect(assigns(:articles)).to eq([article]) # checking if @articles matches array of article we created
    end
  end
end

```

#### To test render view
```rb
 it 'should render the index template' do
      get :index # defining the controller method we want to test
      expect(response).to render_template('index')
    end
```

#### To test status code is correct in response
``` rb
it 'should give status code 200' do
      get :index # defining the controller method we want to test
      expect(response).to have_http_status(:ok)
    end
```

### Hooks 
As we are doing some common task before each test we can use **before** and **after hooks** provided by RSpec.
**Note** : Do not use *let* in hooks.
```rb
before(:each) do
	get :index
end
```
