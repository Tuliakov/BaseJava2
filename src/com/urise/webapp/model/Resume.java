package com.urise.webapp.model;

import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>{

  private final String uuid;
  private final String fullName;

  public Resume(String fullName) {
    this(UUID.randomUUID().toString(), fullName);
  }

  public Resume(String uuid, String fullName) {
    Objects.requireNonNull(uuid, "This field must not be empty");
    Objects.requireNonNull(fullName, "This field must not be empty");
    this.uuid = uuid;
    this.fullName = fullName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Resume resume = (Resume) o;
    return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
  }
  @Override
  public int hashCode() {
    int result = uuid.hashCode();
    result = 31 * result * fullName.hashCode();
    return result;
  }

  public String getUuid() {
    return uuid;
  }

  @Override
  public String toString() {
    return uuid + '(' + fullName + ')';
  }

  @Override
  public int compareTo(Resume o) {
    int comp = fullName.compareTo(o.fullName);
    return comp != 0 ? comp : uuid.compareTo(o.uuid);
  }
}
