If we have some uncommited changes and it have some conflicts to other branch and we need to switch to that branch, git will not let us to switch.

> Stashes are stored in stack so we can pop out one by one in order.

#### git stash
It will **save** our all **uncommited work** and undo all the changes in our working directory to switch.

#### git stash pop
- **Reapply** all the changes from the stash to our working copy.
- I will remove these changes from stash

#### git stash apply
- It can apply the changes to our working directory but it **will not remove** from the stashing area so we can apply these same changes to **multiple branches**. 

#### git stash list
- It gives the list of all the stashes

#### git stash drop stash@{2}
- TO drop an specific stash

#### git stash apply stash@{3}
- To apply a specific stash

git stash push -m "name"



