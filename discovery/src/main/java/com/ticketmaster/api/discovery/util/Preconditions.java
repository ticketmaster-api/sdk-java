package com.ticketmaster.api.discovery.util;


public class Preconditions {

  private Preconditions() {}

  public static void checkNotNull(Object value) {
    if (value == null) {
      throw new NullPointerException();
    }
  }

  public static void checkNotNull(Object value, String message) {
    if (value == null) {
      throw new NullPointerException(message);
    }
  }

  public static void checkArgument(boolean expression, String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkArgument(boolean expression) {
    if (!expression) {
      throw new IllegalArgumentException();
    }
  }

  public static void checkNotEmpty(String value, String message) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkIllegalCharacters(String value, String caracters) {
    for (char c : caracters.toCharArray()) {
      if (value.contains(String.valueOf(c))) {
        throw new IllegalArgumentException("Value cannot contain '" + c + "'.");
      }
    }
  }
}
