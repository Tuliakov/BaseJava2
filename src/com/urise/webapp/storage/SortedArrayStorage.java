package com.urise.webapp.storage;

import java.util.Arrays;
import java.util.Comparator;

import com.urise.webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage{

  private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

  @Override
  protected void fillDeletedElement(int index) {
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(storage, index + 1, storage, index, numMoved);
    }

  }@Override
  protected void insertElement(Resume r, int index) {
    int insertIdx = - index - 1;
    System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
    storage[insertIdx] = r;
  }

  @Override
  protected Integer getSearchKey(String uuid) {
    Resume searchKey = new Resume(uuid, "Maks");
    return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
  }
}
