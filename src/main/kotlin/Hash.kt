import java.io.File
import java.math.BigInteger
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.security.MessageDigest

fun hashFlag() {
    val url = "https://0ijq1i6sp1.execute-api.us-east-1.amazonaws.com/dev/hash"

    val httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build()
    val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    val (_, hash, salt) = (httpResponse.body().split("\"")[1]).split(":")

    val md = MessageDigest.getInstance("MD5")
    File("src/main/resources/common-7-letter-words.txt").forEachLine {
        val word = it.lowercase()
        if (BigInteger(1, md.digest("${word}${salt}".toByteArray())).toString(16) == "$hash") {
            println("hash flag is: $word")
        }
    }
}