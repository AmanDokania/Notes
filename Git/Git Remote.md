#### Add a remote
```bash
git remote add <name> <url>
```

#### To view all the remotes
```bash
git remove -v
```

#### Push a branch to remote
```bash
git push origin <branch>
```

#### Checkout Remote Branches
```bash
git checkout origin/main
```

### Fetch
- It will fetch all the changes from the origin remote repositories but it will **not make any changes** to our local workspace.
- Now we have access of all the changes in **origin/branch**

#### Fetch one branch
```bash
git fetch origin <branchname>
```

#### Fetch all the branches
```bash
git fetch origin
```

### Pull
- It will **update our working directory** from the changes pulled by origin.
- It will update our head.
```bash
git pull origin main
```

- Pull can results in **conflicts**.
- When we solve these conflicts our local head will be ahead of origin so we will need to push this **pull merge commit**

> If we donot specify the branch it will pull the current branch
```bash
git pull
```