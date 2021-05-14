package com.thong.converter;


public interface  IConverter<E,V> {
	 E toEntity(V o) throws Exception;
	 V toDTO(E o);
}
