import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

const val rootUrl = "https://0ijq1i6sp1.execute-api.us-east-1.amazonaws.com/dev/start?q="
const val readmeSelectQuery = "select * from readme"

val httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build()

fun init() {
    val url = rootUrl + URLEncoder.encode(readmeSelectQuery, "UTF-8")
    val httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build()
    val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    val jsonResponse: List<List<String>> = jacksonObjectMapper().readValue(httpResponse.body())
    val welcomeMessage = String(Base64.getDecoder().decode(jsonResponse[0][0]))

    println(welcomeMessage)
}