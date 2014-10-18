/**
 * Copyright 2014 Kay Erik Münch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.kay_muench.imageinformationprovider;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlgraphics.util.io.Base64EncodeStream;

public final class ImageDataUriProvider {

	public final String provideFor(ImageInformation info) throws IOException {
		String dataUri = null;
		try {
			dataUri = this.dataUri(info.getMimeType(),
					new URL(info.getUri()).openStream());
		} catch (MalformedURLException e) {
			dataUri = this.dataUri(info.getMimeType(),
					new FileInputStream(info.getUri()));
		}
		return dataUri;
	}

	private String dataUri(final String mimeType, final InputStream is)
			throws MalformedURLException, IOException {
		final String data = this.base64Content(is);
		if (data == null || data.isEmpty())
			return null;
		return "data:" + mimeType + ";base64," + data;
	}

	private String base64Content(final InputStream is)
			throws MalformedURLException, IOException {
		String content = null;
		ByteArrayOutputStream os = null;
		Base64EncodeStream es = null;
		try {
			os = new ByteArrayOutputStream();
			es = new Base64EncodeStream(os);
			byte[] data = new byte[1024];
			while (true) {
				int len = is.read(data, 0, data.length);
				if (len == -1) {
					break;
				}
				es.write(data, 0, len);
			}
		} finally {
			if (es != null) {
				try {
					es.close();
				} catch (IOException e) {
				}
			}
			if (os != null) {
				try {
					os.close();
					content = os.toString();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

		return content;

	}

}
