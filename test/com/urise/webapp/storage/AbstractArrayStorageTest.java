package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

  public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    protected AbstractArrayStorageTest(Storage storage) {
      super(storage);
    }

  @Test(expected = StorageException.class)
  public void saveOverflow() throws Exception {
    try {
      for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
        storage.save(new Resume("Name"));
      }
    } catch (StorageException e) {
      Assert.fail("Overflow occurred!");
    }
    storage.save(new Resume("Overflow"));
  }
}