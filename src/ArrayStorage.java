import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
  private Resume[] storage = new Resume[10000];
  private int size = 0;

  public void clear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  public void save(Resume r) {
    storage[size] = r;
    size++;
  }

  public Resume get(String uuid) {
    for (int i = 0; i < size; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return storage[i];
      }
    }
    return null;
  }

  public void delete(String uuid) {
    for (int i = 0; i < size; i++) {
      if (storage[i].getUuid().equals(uuid)) {
        storage[i] = null;
        size--;
      }
    }
    storage = Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  public Resume[] getAll() {
    Resume[] notNullResume = new Resume[size];
    for (int i = 0; i < size; i++) {
      notNullResume[i] = storage[i];
    }
    return notNullResume;
  }

  public int size() {
    return size;
  }
}
