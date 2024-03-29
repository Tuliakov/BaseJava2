package com.urise.webapp.storage;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public class AbstractStorageTest {
  protected Storage storage;

  private static final String UUID_1 = "uuid1";
  private static final Resume RESUME_1 = new Resume(UUID_1, "Name");

  private static final String UUID_2 = "uuid2";
  private static final Resume RESUME_2 = new Resume(UUID_2, "Name1");

  private static final String UUID_3 = "uuid3";
  private static final Resume RESUME_3 = new Resume(UUID_3, "Name2");

  private static final String UUID_4 = "uuid4";
  private static final Resume RESUME_4 = new Resume(UUID_4, "Name3");

  protected AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  @Before
  public void setUp() throws Exception {
    storage.clear();
    storage.save(RESUME_1);
    storage.save(RESUME_2);
    storage.save(RESUME_3);
  }

  @Test
  public void size() throws Exception {
    assertSize(3);
  }

  @Test
  public void clear() throws Exception {
    storage.clear();
    assertSize(0);
  }

  @Test
  public void update() throws Exception {
    Resume newResume = new Resume(UUID_1, "New Name");
    storage.update(newResume);
    assertEquals(newResume, storage.get(UUID_1));
  }

  @Test(expected = NotExistStorageException.class)
  public void updateNotExist() throws Exception {
    storage.get(UUID_4);
  }

  @Test
  public void getAllSorted() throws Exception {
    List<Resume> list = storage.getAllSorted();
    assertEquals(3, list.size());
    assertEquals(Arrays.asList(RESUME_1, RESUME_2, RESUME_3), list);
  }

  @Test
  public void save() throws Exception {
    storage.save(RESUME_4);
    assertSize(4);
    assertGet(RESUME_4);
  }

  @Test(expected = StorageException.class)
  public void saveExist() throws Exception {
    storage.save(RESUME_1);
  }

  @Test(expected = NotExistStorageException.class)
  public void delete() throws Exception {
    storage.delete(UUID_1);
    assertSize(2);
    storage.get(UUID_1);
  }

  @Test(expected = NotExistStorageException.class)
  public void deleteNotExist() throws Exception {
    storage.delete(UUID_4);
  }

  @Test
  public void get() throws Exception {
    assertGet(RESUME_1);
    assertGet(RESUME_2);
    assertGet(RESUME_3);
  }

  @Test(expected = NotExistStorageException.class)
  public void getNotExist() throws Exception {
    storage.get(UUID_4);
  }

  private void assertGet(Resume r) {
    assertEquals(r, storage.get(r.getUuid()));
  }

  private void assertSize(int size) {
    assertEquals(size, storage.size());
  }
}
