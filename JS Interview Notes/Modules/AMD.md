```js
define(['module1', 'module2'),
	function(module1Import, module2Import){
		var fight = module1Import.fight
		var module2 = module2Import
	funciton dance(){
	
	}
	return {
		dance: dance
	};
	}
```

- It helps to load modules ascncronously
- It was used in requirejs