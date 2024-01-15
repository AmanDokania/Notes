- In HTTP request of **POST** we need to add `reportProgress: true` and `observe: 'events'`
```js
this.http.post('http://localhost:3000/api/upload', imageData, {
	  reportProgress: true,
	  observe: 'events',
});
```

- In component we subscribe to this observable
```js
 this.fileUploadService
      .uploadFile(
        this.signupForm.get('username')?.value,
        this.signupForm.get('password')?.value,
        this.files[0].file
      )
      .subscribe({
        next: (event: HttpEvent<any>) => {
          switch (event.type) {
            case HttpEventType.Sent:
              console.log('Request has been made!');
              break;
            case HttpEventType.ResponseHeader:
              console.log('Response header has been received!');
              break;
            case HttpEventType.UploadProgress:
              if (event.total)
                this.progress = Math.round((event.loaded / event.total) * 100);
              console.log(`Uploaded! ${this.progress}%`);
              break;
            case HttpEventType.Response:
              console.log('User successfully created!', event.body);
          }
        },
        error: (error) => {
          console.log(error);
        },
      });
  }
```

- Because we use `observe: 'event'`, when we subscribe we will get `event` in **next**.
- Now using **event.type** we can find http event
- To grab upload progress event name is **HttpEventType.UploadProgress**
- And to get *total* and *loaded* size use
	1. **event.loaded** : size of part of file uploaded.
	2. **event.total** : total size of the file
We can find out percent by these two values.