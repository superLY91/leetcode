---
name: readme-commit-push
description: Keeps the LeetCode README catalog in sync, commits the current repository changes, and pushes to the tracked remote branch. Use when the user asks to update README, commit code, push changes, publish current repo state, or repeat the LeetCode repo release workflow.
---

# README Commit Push

## Quick Start

Use this workflow for the LeetCode repository when the user asks to update the problem catalog and publish the current code.

1. Inspect repository state:

```sh
git status --short --branch
```

2. Scan current problem files:

```sh
find src/j src/k -type f \( -name '*.java' -o -name '*.kt' \) | sort
find src/k/solutions -type f | sort
```

3. Update `README.md` so it reflects the current repository:

- Add new problems under the best primary topic.
- Fix links for moved or renamed files.
- Mark completed implementations as `✅`.
- Mark created but incomplete implementations as `🚧`.
- Update totals in the progress table.

4. Review what will be committed:

```sh
git status --short
git diff --stat
git diff
```

5. Stage, commit, and push:

```sh
git add -A
git commit -m "Update problem catalog"
git push
```

## README Rules

Use one row per LeetCode problem. If both Java and Kotlin exist, keep them in the same row.

Difficulty should come from the known LeetCode difficulty when available. Use the filename only as a hint, because some repository filenames may contain stale difficulty markers:

- `_0042_H_...` means `Hard`
- `_0042_M_...` means `Medium`
- `_0042_E_...` means `Easy`

If difficulty is unclear, infer it from the source directory or the known difficulty already used in the repository, then keep the README internally consistent.

Keep links relative to the repository root.

## Completion Rules

Use `✅` when the source file has a real implementation.

Use `🚧` when the file exists but the method is still a stub, for example:

```kotlin
return 0
```

Use `-` when that language has no implementation file.

## Commit Rules

Before committing, make sure the user asked to commit the current repository state. Do not revert unrelated changes.

Use `git add -A` only when the user asked to submit the current repo code. If the user asks for a narrower commit, stage only those files.

Prefer concise commit messages:

- `Update problem catalog`
- `Add trapping rain water solution`
- `Sync LeetCode solutions`

After pushing, report the commit hash and whether the branch is synced with its upstream.
