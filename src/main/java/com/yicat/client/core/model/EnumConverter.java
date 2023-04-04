package com.yicat.client.core.model;

/**
 * Enum json converter. All enums will implement this interface.
 * Enum which implements this interface should also contain static method for deserialization.
 * See {@link PatchOperation#from}
 * It is needed just to avoid hard dependencies on Jackson library
 *
 * @param <T> enum type
 */
public interface EnumConverter<T extends Enum<T>> {
    Object to(T v);
}
