- It can be used to switch to a branch
#### git checkout branchname

- To go to a specific commit do
```bash
git checkout <commitID>
```
- It will take us to that commit.
- And it will say **head is detached.**

From a detached head we can do these things.
- Examine the code.
- Leave and go back to the branch (**reatach head**).
- Create a new branch from that detached head. Now head is **no longer detached**, we can do commits.  

Go back to specific no. of commits from head.
#### git checkout HEAD~1 
go back to one previous commit

#### git switch -1
get back to branch or **reatach head**.

#### Discard the changes to the file
- **git checkout HEAD file.txt**
- **git checkout -- file.txt**