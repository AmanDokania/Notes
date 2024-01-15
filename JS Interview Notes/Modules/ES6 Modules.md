- native keyword *import* `import moudle1 from 'moduel1' `
- native **export**.
```js
export function jump(){

}
```

#### **fight()** is exported from this file
```js

// This is private it will not be exported
const myData = 10

// This function is exported
export function fight(){

}

```

```js
// named import
import {fight} from 'path/filename'
```

#### Export Default
```js
export default function myFunc(){

}
```
