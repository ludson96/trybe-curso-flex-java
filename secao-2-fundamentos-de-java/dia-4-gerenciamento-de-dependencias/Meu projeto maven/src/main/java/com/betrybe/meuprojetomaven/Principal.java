package com.betrybe.meuprojetomaven;

import com.google.common.base.Joiner;

public class Principal {

  public static void main(String[] args) {
    String result = Joiner.on(", ").skipNulls().join("Olá", null, "mundo");
    System.out.println(result);
  }
}