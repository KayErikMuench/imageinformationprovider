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

public final class ImageInformation {

	private String uri;
	private String mimeType;
	private String alt;
	private int width;
	private int height;
	
	public ImageInformation(String uri, String mimeType, String alt, int width,
			int height) {
		this.uri = uri;
		this.mimeType = mimeType;
		this.alt = alt;
		this.width = width;
		this.height = height;
	}

	public final String getUri() {
		return uri;
	}

	public final String getMimeType() {
		return mimeType;
	}

	public final String getAlt() {
		return alt;
	}

	public final int getWidth() {
		return width;
	}

	public final int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "ImageInformation [uri=" + uri + ", mimeType=" + mimeType
				+ ", alt=" + alt + ", width=" + width + ", height=" + height
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alt == null) ? 0 : alt.hashCode());
		result = prime * result + height;
		result = prime * result
				+ ((mimeType == null) ? 0 : mimeType.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageInformation other = (ImageInformation) obj;
		if (alt == null) {
			if (other.alt != null)
				return false;
		} else if (!alt.equals(other.alt))
			return false;
		if (height != other.height)
			return false;
		if (mimeType == null) {
			if (other.mimeType != null)
				return false;
		} else if (!mimeType.equals(other.mimeType))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

}
