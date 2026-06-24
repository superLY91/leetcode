package k.okx

fun main() {
    println(parseInt("123")) // 123

    println(evaluateAdditions("1+2+13")) // 16

    println(evaluateAdditionsAndMultiplications("3+5*80")) // 403

    println(
        evaluateAdditionsMultiplicationsAndNestedBrackets(
            "30*(4+2*(3+5))"
        )
    ) // 600

    // 更多测试
    println(evaluateAdditionsAndMultiplications("1+2*3")) // 7
    println(evaluateAdditionsAndMultiplications("2*3+4*5")) // 26
    println(evaluateAdditionsMultiplicationsAndNestedBrackets("(1+2)*(3+4)")) // 21
    println(evaluateAdditionsMultiplicationsAndNestedBrackets("2*(3+4*5)")) // 46
}

// Assumption: The input is always valid, i.e., a result can always be evaluated
// There is NO space or any other character. Only digits and the mentioned operators

// Step 1: Implement the logic to parse a MULTI-DIGIT (e.g. "100") string into an Int
// TRY NOT to use library functions to implement this requirement (e.g. `Integer.parseInt()`, `String.toInt()`)
// e.g. `rawString` is "123" and the function should return an Int 123
/**
 * 原因是字符在底层有编码值，比如 ASCII / Unicode 里：
 *
 * '0' = 48
 * '1' = 49
 * '2' = 50
 * '3' = 51
 *
 * 所以：
 *
 * '3' - '0'
 * = 51 - 48
 * = 3
 */
fun parseInt(rawString: String): Int {
    var result = 0

    // "123"
    for (ch in rawString) {
        val digit = ch - '0' // 1, 2, 3
        result = result * 10 + digit // 12, 123
    }

    return result
}


// Step 2: Implement the logic to evaluate the formula containing:
// * MULTI-DIGIT INTEGERS (e.g. "100")
// * ADDITIONS (+)
// e.g. `formula` is "1+2+13" and the function should return an Int 16
fun evaluateAdditions(formula: String): Int {
    val sLength = formula.length // 长度
    var sum = 0 // 总和
    var startIndex = 0 // 数字开始下标

    for (i in formula.indices) { // 1,3
        if (formula[i] == '+') {
            // 截取的部分不包括i
            val numberString = formula.substring(startIndex, i)
            sum += parseInt(numberString)
            startIndex = i + 1
        }
    }

    sum += parseInt(formula.substring(startIndex)) // 13
    return sum
}


// Step 3: Implement the logic to evaluate the formula containing:
// * MULTI-DIGIT INTEGERS (e.g. "100")
// * ADDITIONS (+)
// * MULTIPLICATIONS (*)
// Note the operator precedence, i.e. we need to perform multiplication FIRST
// before additions
// e.g. `formula` is "3+5*80" and the function should return an Int 403
fun evaluateAdditionsAndMultiplications(formula: String): Int {
    return ExpressionParser(formula).parseExpression()
}


// Step 4: Implement the logic to evaluate the formula containing:
// * MULTI-DIGIT INTEGERS (e.g. "100")
// * ADDITIONS (+)
// * MULTIPLICATIONS (*)
// * NESTED BRACKETS (e.g. 30*(4+2*(3+5)))
// e.g. `formula` is "30*(4+2*(3+5))" and the function should return 600
fun evaluateAdditionsMultiplicationsAndNestedBrackets(formula: String): Int {
    return ExpressionParser(formula).parseExpression()
}

/**
 * 表达式解析器
 *
 * 核心思想：
 *
 * expression 处理加法
 * term       处理乘法
 * factor     处理数字或者括号
 *
 * 优先级关系：
 *
 * 括号 > 乘法 > 加法
 */
class ExpressionParser(
    private val formula: String
) {
    private var index = 0

    /**
     * expression = term + term +term
     *
     * 这一层只处理加法
     */
    fun parseExpression(): Int {
        var result = parseTerm()

        while (index < formula.length && formula[index] == '+') {
            index++ // 跳过+
            result += parseTerm()
        }
        return result
    }

    /**
     * term = factor * factor * factor
     *
     * 这一层只处理乘法
     * 因为 parseExpression 调用 parseTerm，
     * 所以乘法天然比加法优先。
     */
    private fun parseTerm(): Int{
        var result = parseFactor()

        while (index < formula.length && formula[index] == '*') {
            index++ // 跳过 '*'
            result *= parseFactor()
        }

        return result
    }

    /**
     * factor = 数字 或者 括号表达式
     *
     * 如果遇到 '('，就递归解析括号里的 expression。
     */
    private fun parseFactor(): Int {
        return if (formula[index] == '(') {
            index++ // 跳过 '('

            // 算出了括号里的表达式
            val result = parseExpression()

            index++ // 跳过 ')'

            result
        } else {
            parseNumber()
        }
    }

    /**
     * 解析多位数字
     *
     * 例子：
     * "123" -> 123
     */
    private fun parseNumber(): Int {
        var result = 0
        while (index < formula.length && formula[index].isDigit()) {
            val digit = formula[index] - '0'
            result = result * 10 + digit
            index++
        }
        return result
    }
}