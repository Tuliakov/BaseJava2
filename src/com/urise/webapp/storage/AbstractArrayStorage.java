package com.urise.webapp.storage;

import java.util.Arrays;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
      throw new NotExistStorageException(r.getUuid());
    }
    storage[index] = r;
  }

  public Resume[] getAll() {
    return Arrays.copyOfRange(storage, 0, size);
  }

  public void save(Resume r) {
    int index = getIndex(r.getUuid());
    if (index < 0) {
      if (size == STORAGE_LIMIT) {
        throw new StorageException("Storage overflow", r.getUuid());
      }
      insertElement(r, index);
      size++;
    } else {
      throw new ExistStorageException(r.getUuid());
    }
  }

  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index < 0) {
      throw new NotExistStorageException(uuid);
    }
    fillDeletedElement(index);
    storage[size - 1] = null;
    size--;
  }

  public Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index < 0) {
      throw new NotExistStorageException(uuid);
    }
    return storage[index];
  }
}
