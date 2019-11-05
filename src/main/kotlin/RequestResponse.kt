import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.StringBuilder

enum class Method { GET, PUT, POST, DELETE }

val bytes = "Hello World".toByteArray().inputStream()
val bin = bytes.bufferedReader()

class Request(input: InputStream) {
    val resource: String
    val method: Method
    init {
        val reader = input.bufferedReader()
        val line = reader.readLine()
        val parts = line.split(" ")
        resource = parts[1]
        method = Method.valueOf(parts[0])

    }
}

class Response(val output: OutputStream) {

    val body = StringBuilder()

    fun append(text: String) {
        body.append(text)
        print("test body:  " +body)
    }
    fun send() {

        val writer = output.bufferedWriter()
        val responseText = """
        HTTP/1.1 200 OK
        Content-Type: text/html; charset=UTF-8
        Content-Length: ${body.length}
        Connection: close
        
    """.trimIndent()
        writer.append(responseText)
        writer.newLine()
        writer.append(body)
        writer.flush()
        writer.close()


    }

}
fun main (){
    val output = ByteArrayOutputStream(1024)
    val writer = output.bufferedWriter()

}
