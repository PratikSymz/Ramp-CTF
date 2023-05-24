import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.charset.Charset

const val FLAG_SIZE = 7

fun streamFlag() {
    val url = "https://0ijq1i6sp1.execute-api.us-east-1.amazonaws.com/dev/stream"

    val flagCharacters = mutableSetOf<Char>()

    while (flagCharacters.size < FLAG_SIZE) {
        val stream: InputStream = URL(url).openStream()
        val char = String(stream.readBytes(), Charset.forName("UTF-8"))[1]
        flagCharacters.add(char)
    }

    File("src/main/resources/common-7-letter-words.txt").forEachLine {
        val word = it.lowercase()
        if (validWord(flagCharacters, word)) {
            println("stream flag is: $word")
        }
    }
}

fun validWord(chars: Set<Char>, word: String): Boolean {
    for (char in chars) {
        if (!word.contains(char)) return false
    }
    return true
}