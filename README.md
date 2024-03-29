# HttpResponseBackedInputStream
An InputStream backed by an <a href="https://hc.apache.org/">HttpClient</a> CloseableHttpResponse. 

The purpose of this class is to help prevent the occurrence of connection leaks when working with <a href="https://hc.apache.org/">Apache HttpClient</a>, without having to resort to using HttpClient's <a href="https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/client/ResponseHandler.html">ResponseHandler</a>. For more information, please read <a href="http://blog.michaelscepaniak.com/how-to-read-httpclient-logging-and-prevent-connection-leaks">How to Read HttpClient Logging and Prevent Connection Leaks</a>.

Successfully tested and used with <a href="https://hc.apache.org/">HttpClient</a> v4.3.x
