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

import java.net.URI;

import org.apache.xmlgraphics.image.loader.ImageContext;
import org.apache.xmlgraphics.image.loader.ImageInfo;
import org.apache.xmlgraphics.image.loader.ImageManager;
import org.apache.xmlgraphics.image.loader.ImageSessionContext;
import org.apache.xmlgraphics.image.loader.ImageSize;
import org.apache.xmlgraphics.image.loader.impl.DefaultImageContext;
import org.apache.xmlgraphics.image.loader.impl.DefaultImageSessionContext;

/**
 * Provides informations about an image.
 * 
 */
public final class ImageInformationProvider {
	private static final String ERR_MSG = "Can't get image information";
	private ImageManager imageManager;
	private ImageSessionContext sessionContext;

	public ImageInformationProvider() {
		ImageContext imageContext = new DefaultImageContext();
		imageManager = new ImageManager(imageContext);
		sessionContext = new DefaultImageSessionContext(
				imageManager.getImageContext(), null);
	}

	public final ImageInformation provideFor(String uri) {
		try {
			final ImageInfo info = imageManager.getImageInfo(uri,
					sessionContext);
			final ImageSize size = info.getSize();

			return new ImageInformation(uri, info.getMimeType(), "",
					size.getWidthPx(), size.getHeightPx());
		} catch (Exception e) {
			throw new RuntimeException(ERR_MSG, e);
		}
	}

	public ImageInformation provideFor(URI uri) {
		try {
			return this.provideFor(uri.toASCIIString());
		} catch (RuntimeException e) {
			if (e.getMessage().contains(ERR_MSG)
					&& uri.getScheme().equals("file")) {
				return this
						.provideFor(uri.getSchemeSpecificPart().substring(2));
			}
			throw (e);
		}
	}

}
