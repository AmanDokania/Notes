
- ## Remote Proxy - 
	> When we want to interact with remote resource a proxy will intersept our request and do the actual request on our behalf
- ### Virtual Proxy
	> Lazy initialization (virtual proxy). This is when you have a heavyweight service object that wastes system resources by being always up, even though you only need it from time to time
- ### Protection Proxy
	> Access control (protection proxy). This is when you want only specific clients to be able to use the service object; for instance, when your objects are crucial parts of an operating system and clients are various launched applications (including malicious ones).
- ### Caching Proxy
	> Caching request results (caching proxy). This is when you need to cache results of client requests and manage the life cycle of this cache, especially if results are quite large.
	

IBookParser
```java
interface IBookPaser{
	int getNumPages()
}
```


BookParser
```java
class BookParser : IBookParser{
	BookParser(String book){
		... // Expensive parsing 
	}

	public int getNumPages(){
		...
	}
}
```

LazyBookParserProxy
```java
class LazyBookParserProxy{
	private bookParser = null;

	LazyBookParserProxy(String book){
		this.book = book
	}

	public int getNumPages(){
		if (bookParser == null){
			this.bookParser = new BookParser(this.book)
		}
		return this.bookParser.getNumPages
	}
}
```

Now this proxy is _intelligent_ it will not directly instantiate the book parser which is an expensive task it will wait until it actually required like calling `getNumPages()`