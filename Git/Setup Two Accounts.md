1. Create a ssh public and private key using this command.
	`ssh-keygen -o -t rsa -C "email@example.com"`
2. It will ask for a name. Give a name to the keys.
3. Create two keys **work** & **personal**.
4. So, our file will be like (`work`, `work.pub`) & (`personal`, `personal.pub`)
5. Add the key in ssh agent `ssh-add ~/.ssh/work`
6. Add the key in ssh agent `ssh-add ~/.ssh/personal`
7. Now the copy the public key from `.pub` files and add it to github accounts.
8. Now create a config file in `.ssh` folder and edit this file like this.
```
Host github.com-personal
   HostName github.com
   User git
   IdentityFile ~/.ssh/Kushal
   
# Work account
Host github.com-work   
   HostName github.com
   User git
   IdentityFile ~/.ssh/Work
```

> **NOTE** : If this give error than add permission to ssh folder: `chmod 700 ~/.ssh` & `chmod 600 ~/.ssh/*` 

ERROR:
```bash
Permissions 0664 for '/home/kushal/.ssh/KushalKanungo' are too open.
It is required that your private key files are NOT accessible by others.
This private key will be ignored.
```
SOLUTION: `chmod 400 ~/.ssh/KushalKanungo`


