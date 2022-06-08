package com.urise.webapp.storage;

import java.util.Arrays;

import com.urise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage{
  protected static final int STORAGE_LIMIT = 10000;

  protected abstract void fillDeletedElement(int index);

  protected abstract void insertElement(Resume r, int index);

  protected abstract int getIndex(String uuid);

  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int size = 0;

  public int size() {
    return size;
  }

  public void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if (index < 0) {
      System.out.println("Resume " + r.getUuid() + " not exist");
    } else {
      storage[index] = r;
    }
  }

  public Resume[] getAll() {
    return Arrays.copyOfRange(storage, 0, size);
  }

  public void save(Resume r) {
    int index = getIndex(r.getUuid());
    if (index >= 0) {
      System.out.println("Resume " + r.getUuid() + " already exist");
    } else if (size == STORAGE_LIMIT) {
      System.out.println("Storage overflow");
    } else {
      insertElement(r, index);
      size++;
    }
  }

  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index < 0) {
      System.out.println("Resume " + uuid + " not exist");
    } else {
      fillDeletedElement(index);
      storage[size - 1] = null;
      size--;
    }
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

  protected int findResumeIndex(String uuid) {
    Resume searchKey = new Resume();
    searchKey.setUuid(uuid);
    return Arrays.binarySearch(storage, 0, size, searchKey);
  }
}
