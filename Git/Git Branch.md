#### To view all branches
```bash
git branch
```

#### Create an branch
```bash
git brach branchname
```
> It will create a branch but it will not switch to it 

#### Creating and switching 
```bash
git brach -c branchname
```

#### To switch to a branch
```bash
git switch branchname
```

**Note**: If we try to **switch** to another brach and we have some **uncommited work** in our current branch then we have only three options.
> IMP: This only happens when there is a file modifiend it will not give error in switching if the file in **untracked.** 
1. Delete al the modified changes
2. Commit the changes 
3. Or [[Git Stash]] the changes

#### Deleting a branch
We have to be on  different branch to delete
```bash
git branch -d branchname
```

#### Renaming Branches
We have to be on same branch to rename
```bash
git branch -m newName
```