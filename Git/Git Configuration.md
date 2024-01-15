#### Username and Password
```bash
git config --gloabal user.name "Kushal Kanungo"
git config --global user.email kushalkhandelwal27@gmail.com
```

#### Default Editor
```bash
git config --global code.editor "code --wait"
```

#### Open Default Config File
```bash
git config --global -e
```

#### For mac 
```bash
git config --global code.autocrlf input
```

#### Default Visual Diff Tools
```bash
git config --global diff.tool vscode

git config --global difftool.vscode.cmd "code --wait --diff $LOCAL $REMOTE"
```