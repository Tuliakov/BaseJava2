package com.urise.webapp.storage;

import java.util.Arrays;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

  public void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  public void save(Resume r) {
    int resumeIndex = findResumeIndex(r.getUuid());
    if (resumeIndex == -1 && size < storage.length) {
      storage[size] = r;
      System.out.println("Resume with uuid : " + r.getUuid() + " saved!");
      size++;
    } else {
      System.out.println("ERROR. Resume already exists!");
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

  public void delete(String uuid) {
    int resumeIndex = findResumeIndex(uuid);
    if (resumeIndex != -1) {
      System.out.println("Resume with uuid : " + uuid + " deleted!");
      storage[findResumeIndex(uuid)] = null;
      System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex);
      size--;
    } else {
      System.out.println("ERROR. Resume with uuid : " + uuid + " not found!");
    }
  }

  public Resume[] getAll() {
    return Arrays.copyOf(storage, size);
  }

  public void update(Resume resume) {
    int resumeIndex = findResumeIndex(resume.getUuid());
    if (resumeIndex != -1) {
      storage[resumeIndex] = resume;
      System.out.println("Resume with uuid : " + resume.getUuid() + " updated");
    } else {
      System.out.println("ERROR. Resume with uuid : " + resume.getUuid() + " not found!");
    }
  }

  protected int findResumeIndex(String uuid) {
    if (uuid != null) {
      for (int i = 0; i < size; i++) {
        if (uuid.equals(storage[i].getUuid())) {
          return i;
        }
      }
    }
    return -1;
  }
}

