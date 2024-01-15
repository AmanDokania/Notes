```js
var module1 = require('moduel1')
var module2 = require('moduel2') 

function fight(){

}

module.exports = {
	fight: fight
}

```

- It is **syncronous**.
- So it waits until the module imported
- **Syncronous** code is very dangerous in browsers.