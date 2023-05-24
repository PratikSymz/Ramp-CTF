import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun browserFlag() {
    val url = "https://0ijq1i6sp1.execute-api.us-east-1.amazonaws.com/dev/browser"

    val httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build()
    val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    val exceptionMessage = httpResponse.body()
    val userAgent = exceptionMessage
        .substringAfter("(expected_user_agent)\\n")
        .substringBefore("\"")

    val flagHttpRequest = HttpRequest.newBuilder().uri(URI.create(url)).setHeader("User-Agent", userAgent).build()

    println("browser flag is: ${httpClient.send(flagHttpRequest, HttpResponse.BodyHandlers.ofString()).body()
        .substring(1, FLAG_SIZE + 1)}")
}