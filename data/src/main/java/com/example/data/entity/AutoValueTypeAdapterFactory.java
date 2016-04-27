package com.example.data.entity;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class AutoValueTypeAdapterFactory implements TypeAdapterFactory {

  @Override public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    Class<? super T> rawType = type.getRawType();
    if (rawType.equals(RepoEntity.class)) {
      return (TypeAdapter<T>) RepoEntity.typeAdapter(gson);
    }
    return null;
  }
}