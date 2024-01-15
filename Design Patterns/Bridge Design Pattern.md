> **Bridge** is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.

![[Bridge Pattern Diagram]]

So instead of creating all possible classes combination of 2 sets of classes like 
- **LongFormBookView**,
- **LongFormAlbumView**, 
- **ShortFormBookView**, 
- **ShortFormAlbumView** etc.
We have created two abstracts that have composition in between.
Now we can create an object of LongFormView that has a Book Object.

The Interface will have some methods that will be used by Abstraction Class

> NOTE: In other words, we divide the code in two parts when is platform dependent in this case **MediaResource** and platform independent code here **Views**. And use them to create different combination of them instead of creating class for all possible combination classes.

## Pseudo Code

Abstract Class
```java
abstract class View{

	private IResource resource;

	public View(IResource){
		this.resource = IResource;
	}

	public string show();
}
```

```java

class LongForm: View{
	
	public override String show(){
		...
	}
}

```

INTERFACE
```java
interface IResource{
	String snippet();
	String title();
	String image();
	String url();
}
```

```java
class ArtistResource : IResource{
	Artist artist;
	public ArtistResource(Artist a){
		this.artist = a
	}

	public String snippet(){
		return artist.bio
	}

	public String title(){
		return this.artist.f_name+" "+this.artist.l_name
	}
}
```
