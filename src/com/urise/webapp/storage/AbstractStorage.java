package com.urise.webapp.storage;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage<SK> implements Storage{
  private final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

  protected abstract SK getSearchKey(String uuid);

  protected abstract List<Resume> doCopyAll();

  protected abstract void doUpdate(Resume r, SK searchKey);

  protected abstract boolean isExist(SK searchKey);

  protected abstract void doSave(Resume r, SK searchKey);

  protected abstract Resume doGet(SK searchKey);

  protected abstract void doDelete(SK searchKey);

  public void update(Resume r) {
    LOG.info("Update " + r);
    SK searchKey = getExistedSearchKey(r.getUuid());
    doUpdate(r, searchKey);
  }

  public void save(Resume r) {
    LOG.info("Save " + r);
    SK searchKey = getNotExistedSearchKey(r.getUuid());
    doSave(r, searchKey);
  }

  public void delete(String uuid) {
    LOG.info("Delete " + uuid);
    SK searchKey = getExistedSearchKey(uuid);
    doDelete(searchKey);
  }

  public Resume get(String uuid) {
    LOG.info("Get " + uuid);
    SK searchKey = getExistedSearchKey(uuid);
    return doGet(searchKey);
  }

  private SK getExistedSearchKey(String uuid) {
    SK searchKey = getSearchKey(uuid);
    if (!isExist(searchKey)) {
      LOG.warning("Resume " + uuid + " not exist");
      throw new NotExistStorageException(uuid);
    }
    return searchKey;
  }

  private SK getNotExistedSearchKey(String uuid) {
    SK searchKey = getSearchKey(uuid);
    if (isExist(searchKey)) {
      LOG.warning("Resume " + uuid + " already exist");
      throw new ExistStorageException(uuid);
    }
    return searchKey;
  }


  @Override
  public List<Resume> getAllSorted() {
    LOG.info("GetAllSorted ");
    List<Resume> list = doCopyAll();
    Collections.sort(list);
    return list;
  }
}
