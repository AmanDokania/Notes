- If we stage a file and modify this file before then commiting then it can be be at both place simulatnously.


## Adding Items to git
- **git add .**
- **git add filename.txt**
- `git add *.txt`

## Commit
- **git commit -m 'Initial Commit'** : When we want to write a short message
- **git commit** : It will open editor to type message 
- **git commit -a** :
	Skipping the stagging step and directly adding and commiting in one step, but it only works for **tracked files.** We have to add new or untracted file manually.

## Removing File
- **git rm file.txt**
- **git rm -r --cached bin/file.txt** : removed from staging area if file is already tracked and then added to *.gitignore.*

## Renaming File
- **git mv before.txt after.txt**

## Ignoring File
- create **.gitignore** file
- add file, types, dir which we want to ignore

## Status
- **git status**
- **git status -s** : for short view

## View Statged 
- **git diff --staged** : not very friendly use *VSCODE*.

## Visual Diff Tools
- [[Git Configuration#Default Visual Diff Tools |Config]]
- **git difftool --staged**
- **git difftool**