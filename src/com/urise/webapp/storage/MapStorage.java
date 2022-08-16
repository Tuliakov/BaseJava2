package com.urise.webapp.storage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.urise.webapp.model.Resume;

public class MapStorage extends AbstractStorage{

  private final Map<String, Resume> map = new LinkedHashMap<>();

  @Override
  protected String getSearchKey(String uuid) {
    return uuid;
  }

  @Override
  protected void doUpdate(Resume r, Object searchKey) {
    map.put(searchKey.toString(), r);
  }

  @Override
  protected boolean isExist(Object searchKey) {
    return map.containsKey(searchKey);
  }

  @Override
  protected void doSave(Resume r, Object searchKey) {
    map.put(searchKey.toString(), r);
  }

  @Override
  protected Resume doGet(Object searchKey) {
    return map.get(searchKey.toString());
  }

  @Override
  protected void doDelete(Object searchKey) {
    map.remove(searchKey.toString());
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public Resume[] getAll() {
    return map.values().toArray(new Resume[0]);
  }

  @Override
  public int size() {
    return map.size();
  }
}
