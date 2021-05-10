package com.safetynet.alerts.controller;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.local.LocalData;

/**
 * Class reading data from local json
 * 
 * @see LocalData
 * 
 * @author MikeMatthews
 */
public class LocalDataReader {

	/** name of the file to read (with extention) */
	String fileName;

	public LocalDataReader(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Reads data from local file
	 * 
	 * @return LocalData object
	 * 
	 * @see LocalData
	 */
	public LocalData readFile() {

		LocalData localData = null;
		ObjectMapper mapper = new ObjectMapper();

		TypeReference<LocalData> typeReference = new TypeReference<LocalData>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream(fileName);

		try {
			localData = mapper.readValue(inputStream, typeReference);
		} catch (IOException exception) {
			System.out.println(exception);
		} finally {

			try {
				inputStream.close();
			} catch (IOException ioException) {
				System.out.println(ioException);
			}
		}

		return localData;
	}
}