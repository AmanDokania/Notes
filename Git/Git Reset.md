It will **reset** the **repo** back to a specific commit.

#### Soft RESET
```bash
git reset <commitID>
```
- It will remove the commits after this.
- We will not loose the changes but commits will be gone.

#### Hard RESET
```bash
git reset --hard <commit>
```
- It will remove the commits as well as all those cahnges