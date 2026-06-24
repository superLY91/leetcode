package k.common

class TreeNode {
    var value: Int
    var left: TreeNode?
    var right: TreeNode?

    constructor() {
        this.value = 0
        this.left = null
        this.right = null
    }

    constructor(`val`: Int) {
        this.value = `val`
        this.left = null
        this.right = null
    }

    constructor(`val`: Int, left: TreeNode?, right: TreeNode?) {
        this.value = `val`
        this.left = left
        this.right = right
    }
}