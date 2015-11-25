/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * 
 * Need instructions or want more info? I live at https://github.com/hispanic/HttpResponseBackedInputStream
 * Copyright © 2015 Michael Harry Scepaniak
 */
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

/**
 * An InputStream backed by an HTTP response provided by Apache HttpClient. When this InputStream is closed, 
 * steps are implicitly taken to safely and properly close/manage the source HTTP connection.
 * 
 * Instances of this class are intended to be used as the return value from calls to a custom-coded class (not 
 * provided) used to wrap HttpClient. These calls would return content only, as opposed to the full HTTP response object. 
 *
 * @author Michael Harry Scepaniak
 *
 */
public class HttpResponseBackedInputStream extends InputStream
{

        private InputStream backingStream ;
        private CloseableHttpResponse httpResponse ;

        public HttpResponseBackedInputStream(CloseableHttpResponse httpResponse)
                      throws IllegalStateException, IOException
       {
               super();
               this.httpResponse = httpResponse;
               this.backingStream = this.httpResponse.getEntity().getContent();
       }

        @Override
        public void close() throws IOException {
               // Close the backing inputStream.
               super.close();
               this.backingStream.close();

               // Fully consume the backing HTTP response.
               EntityUtils.consumeQuietly(httpResponse.getEntity());

               /*
               * Don't actually close the HTTP response, as this prevents it from being re-used,
               * negatively impacting performance.
               */
               //this.httpResponse.close();
       }

        @Override
        public int read() throws IOException {
               return this.backingStream.read();
       }

        @Override
        public int read(byte[] b) throws IOException {
               return this.backingStream.read(b);
       }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
               return this.backingStream.read(b, off, len);
       }

        @Override
        public long skip(long n) throws IOException {
               return this.backingStream.skip(n);
       }

        @Override
        public int available() throws IOException {
               return this.backingStream.available();
       }

        @Override
        public synchronized void mark(int readlimit) {
               this.backingStream.mark(readlimit);
       }

        @Override
        public synchronized void reset() throws IOException {
               this.backingStream.reset();
       }

        @Override
        public boolean markSupported() {
               return this.backingStream.markSupported();
       }

}
