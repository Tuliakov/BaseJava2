package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage{
  protected static final int STORAGE_LIMIT = 10000;

  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int size = 0;

  public int size() {
    return size;
  }

  public Resume get(String uuid) {
    if (uuid != null) {
      if (findResumeIndex(uuid) != -1) {
        System.out.println("Resume with uuid : " + uuid + " got!");
        return storage[findResumeIndex(uuid)];
      }
    }
    return null;
  }

  protected abstract int findResumeIndex(String uuid);
}
