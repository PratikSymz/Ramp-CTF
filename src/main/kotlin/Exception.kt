import java.io.File
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun exceptionFlag() {
    val url = "https://0ijq1i6sp1.execute-api.us-east-1.amazonaws.com/dev/exception?q="

    File("src/main/resources/common-7-letter-words.txt").forEachLine {
        val word = it.lowercase()
        var sum = 0
        for (char in word) {
            sum += char - 'a' + 1
        }
        if (sum == 42) {
            val httpRequest = HttpRequest.newBuilder().uri(URI.create(url + word)).build()
            val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
            if (!httpResponse.body().contains("raise")) {
                println("exception flag is: ${httpResponse.body().split("\"")[1]}")
                return@forEachLine
            }
        }
    }
}