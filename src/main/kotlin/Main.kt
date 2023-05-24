fun main() {
    init()
    println("------------------------------------------------------------")
    println("After looking at the prompt, we perform some SQL Injection: ")
    sqlInjection()
    println("------------------------------------------------------------")
    println("We now use different strategies for each endpoint: ")
    browserFlag()
    exceptionFlag()
    hashFlag()
    streamFlag()
}