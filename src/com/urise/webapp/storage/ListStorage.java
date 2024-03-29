package com.urise.webapp.storage;

import java.util.ArrayList;
import java.util.List;

import com.urise.webapp.model.Resume;

public class ListStorage extends AbstractStorage<Integer>{

  private final List<Resume> list = new ArrayList<>();

  @Override
  protected Integer getSearchKey(String uuid) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getUuid().equals(uuid)) {
        return i;
      }
    }
    return null;
  }

  @Override
  protected void doUpdate(Resume r, Integer searchKey) {
    list.set(searchKey, r);
  }

  @Override
  protected boolean isExist(Integer searchKey) {
    return searchKey != null;
  }

  @Override
  protected void doSave(Resume r, Integer searchKey) {
    list.add(r);
  }

  @Override
  protected Resume doGet(Integer searchKey) {
    return list.get(searchKey);
  }

  @Override
  protected void doDelete(Integer searchKey) {
    list.remove((searchKey).intValue());
  }

  @Override
  public void clear() {
    list.clear();
  }

  @Override
  protected List<Resume> doCopyAll() {
    return new ArrayList<>(list);
  }

  @Override
  public int size() {
    return list.size();
  }
}
