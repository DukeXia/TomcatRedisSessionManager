package com.leefine.tomcat.redis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Tomcat clustering with Redis data-cache implementation.
 * This class is uses to store and retrieve the HTTP request session object meta-data.
 */
public class SessionMetadata implements Serializable {

	private byte[] attributesHash;

	public SessionMetadata() {
		this.attributesHash = new byte[0];
	}

	public byte[] getAttributesHash() {
		return this.attributesHash;
	}

	public void setAttributesHash(byte[] attributesHash) {
		this.attributesHash = attributesHash;
	}

	public void copyFieldsFrom(SessionMetadata metadata) {
		this.setAttributesHash(metadata.getAttributesHash());
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.attributesHash.length);
		out.write(this.attributesHash);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		int hashLength = in.readInt();
		byte[] attributesHash = new byte[hashLength];
		in.read(attributesHash, 0, hashLength);
		this.attributesHash = attributesHash;
	}
}
