package br.ufmg.dcc.verlab.h3ct0r.asctecAndroidLLP.util;

public class AsctecPackage {

	int packageDescriptor;
	int data[];
	byte dataBytes[];

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public void setPackageDescriptor(int packageDescriptor) {
		this.packageDescriptor = packageDescriptor;
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}

	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}

	public int getPackageDescriptor() {
		return packageDescriptor;
	}
}
