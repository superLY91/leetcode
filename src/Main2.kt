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

/**
 * Step 1:
 * 手写 parseInt
 *
 * 不能使用：
 * String.toInt()
 * Integer.parseInt()
 *
 * 例子：
 * "123" -> 123
 */
fun parseInt(rawString: String): Int {
    var result = 0

    for (ch in rawString) {
        val digit = ch - '0'
        result = result * 10 + digit
    }

    return result
}

/**
 * Step 2:
 * 只支持加法
 *
 * 例子：
 * "1+2+13" -> 16
 */
fun evaluateAdditions(formula: String): Int {
    var sum = 0
    var startIndex = 0

    for (i in formula.indices) {
        if (formula[i] == '+') {
            val numberString = formula.substring(startIndex, i)
            sum += parseInt(numberString)
            startIndex = i + 1
        }
    }

    // 处理最后一个数字
    sum += parseInt(formula.substring(startIndex))

    return sum
}

/**
 * Step 3:
 * 支持加法和乘法，乘法优先
 *
 * 例子：
 * "3+5*80" -> 403
 */
fun evaluateAdditionsAndMultiplications(formula: String): Int {
    return ExpressionParser(formula).parseExpression()
}

/**
 * Step 4:
 * 支持加法、乘法、嵌套括号
 *
 * 例子：
 * "30*(4+2*(3+5))" -> 600
 */
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
     * expression = term + term + term
     *
     * 这一层只处理加法。
     */
    fun parseExpression(): Int {
        var result = parseTerm()

        while (index < formula.length && formula[index] == '+') {
            index++ // 跳过 '+'
            result += parseTerm()
        }

        return result
    }

    /**
     * term = factor * factor * factor
     *
     * 这一层只处理乘法。
     * 因为 parseExpression 调用 parseTerm，
     * 所以乘法天然比加法优先。
     */
    private fun parseTerm(): Int {
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